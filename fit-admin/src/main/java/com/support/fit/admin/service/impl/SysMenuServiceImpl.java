package com.support.fit.admin.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.support.fit.admin.service.SysMenuService;
import com.support.fit.common.core.constant.CacheConstants;
import com.support.fit.common.core.constant.CommonConstants;
import com.support.fit.common.core.constant.enums.MenuTypeEnum;
import com.support.fit.mbg.mapper.SysMenuMapper;
import com.support.fit.mbg.mapper.SysRoleMenuMapper;
import com.support.fit.mbg.model.SysMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final SysRoleMenuMapper sysRoleMenuMapper;
    @Override
    @Cacheable(value = CacheConstants.MENU_DETAILS, key = "#roleId  + '_menu'", unless = "#result == null")
    public Set<SysMenu> findMenuByRoleId(Long roleId) {
        return baseMapper.listMenusByRoleId(roleId);
    }

    @Override
    public List<Tree<Long>> filterMenu(Set<SysMenu> all, Long parentId) {
        List<TreeNode<Long>> collect = all.stream()
                .filter(menu -> MenuTypeEnum.LEFT_MENU.getType().equals(menu.getType()))
                .filter(menu -> StrUtil.isNotBlank(menu.getPath())).map(getNodeFunction()).collect(Collectors.toList());
        Long parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;
        return TreeUtil.build(collect, parent);
    }

    @NotNull
    private Function<SysMenu, TreeNode<Long>> getNodeFunction() {
        return menu -> {
            TreeNode<Long> node = new TreeNode<>();
            node.setId(menu.getMenuId());
            node.setName(menu.getName());
            node.setParentId(menu.getParentId());
            node.setWeight(menu.getSortOrder());
            // 扩展属性
            Map<String, Object> extra = new HashMap<>();
            extra.put("icon", menu.getIcon());
            extra.put("path", menu.getPath());
            extra.put("type", menu.getType());
            extra.put("permission", menu.getPermission());
            extra.put("label", menu.getName());
            extra.put("sortOrder", menu.getSortOrder());
            extra.put("keepAlive", menu.getKeepAlive());
            node.setExtra(extra);
            return node;
        };
    }
}

package com.okta.developer.newconfig;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

/**
 * @author V.Sel
 * @date 2022/6/19
 * @desc
 */
@Service
public class SysPermissionService {

    @Resource
    private SysPermissionRepository sysPermissionRepository;

    public Map<String, String> getAllUrlRole() {
        List<SysPermissionEntity> list = sysPermissionRepository.findAll();
        return list.stream().collect(Collectors.toMap(SysPermissionEntity::getUrl, SysPermissionEntity::getCode));
    }

}

package com.zhonghao.springboot.controller;

import com.zhonghao.springboot.dao.ProviderDao;
import com.zhonghao.springboot.entities.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * 供应商控制层
 */
@Controller
public class ProviderController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ProviderDao providerDao;

    @GetMapping("providers")
    public String list(Map<String,Object> map, String providerName) {
        logger.info("查询供应商: " + providerName);
        Collection<Provider> providers = providerDao.getAll(providerName);
        map.put("providers", providers);
        map.put("providerName", providerName);
        return "provider/list";
    }

    @GetMapping("provider/{pid}")
    public String view(@RequestParam(value = "type", defaultValue = "view")String type, @PathVariable("pid") Integer pid, Map<String, Object> map) {
        logger.info("查询"+ pid + "的供应商详细信息");
        Provider provider = providerDao.getProvider(pid);
        map.put("provider", provider);
        return "provider/"+type;
    }

    //修改供应商信息
    @PutMapping("/provider")
    public String update(Provider provider) {
        logger.info("修改供应商信息: " + provider);
        provider.setCreateDate(new Date());
        providerDao.save(provider);
        //重定向到列表页
        return "redirect:/providers";
    }

    @GetMapping("/provider")
    public String toAddPage() {
        return "provider/add";
    }

    @PostMapping("/provider")
    public String addProvider(Provider provider) {
        //SpringMVC会自动将请求参数与形参对象的属性一一绑定
        //要求：请求参数名要与形参对象的属性名相同
        logger.info("添加供应商信息：" + provider);
        provider.setCreateDate(new Date());
        providerDao.save(provider);
        //添加完成,回到供应商列表页面
        //通过redirect重定向 或forward转发到一个请求地址, / 代表当前项目路径
        return "redirect:/providers";
    }
}

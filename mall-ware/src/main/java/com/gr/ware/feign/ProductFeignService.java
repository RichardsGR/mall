package com.gr.ware.feign;

import com.gr.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("mall-product")
public interface ProductFeignService {
    /**
     * 请求方式有两种写法：1、直接访问product服务；2、通过先访问网关间接访问product服务
     * 如果使用第一种方法，@FeignClient中要写具体服务的名称，请求路径不用写前缀
     * 如果使用第二中方法，@FeignClient中要写网关的服务名称并且要加上前缀 /api
     * @param skuId
     * @return
     */
    @RequestMapping("/product/skuinfo/info/{skuId}")
    public R info(@PathVariable("skuId") Long skuId);

}

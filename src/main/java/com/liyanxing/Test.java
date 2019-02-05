package com.liyanxing;

import com.liyanxing.websiterecommend.mapper.WebsiteMapper;
import com.liyanxing.websiterecommend.pojo.Website;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class Test
{
    @Autowired
    @Qualifier("websiteMapper")
    private WebsiteMapper mapper;

    @GetMapping("/test")
    @ResponseBody
    public List<Website> test()
    {
        return mapper.selectAll();
    }
}
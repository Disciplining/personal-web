package com.liyanxing;

import com.liyanxing.util.DirectoryPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test
{
    @GetMapping("/test")
    @ResponseBody
    public String test()
    {
        System.out.println("子目录：" + DirectoryPath.SOFTWARE_RECOMMEND_CHIL + "\n最终目录：" + DirectoryPath.SOFTWARE_RECOMMEND_DIR);
        return  "子目录：" + DirectoryPath.SOFTWARE_RECOMMEND_CHIL + "\n最终目录：" + DirectoryPath.SOFTWARE_RECOMMEND_DIR;
    }
}
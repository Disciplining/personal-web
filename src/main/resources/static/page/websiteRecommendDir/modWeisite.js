$(  function ()
    {
        bootStrapVid();
    }
);

/**
 * BootStrap表单验证
 */
function bootStrapVid()
{
    $('#form').bootstrapValidator
    (
        {
            fields:
                {
                    name:
                        {

                            validators:
                                {
                                    notEmpty:
                                        {
                                            message:'网站名不能为空'
                                        },
                                    stringLength:
                                        {
                                            max : 16,
                                            message : '最多16个字符'
                                        }
                                }
                        },
                    introduction:
                        {

                            validators:
                                {
                                    notEmpty:
                                        {
                                            message:'网站介绍不能为空'
                                        },
                                    stringLength:
                                        {
                                            max : 256,
                                            message : '最多256个字符'
                                        }
                                }
                        },
                    officialWeb:
                        {

                            validators:
                                {
                                    notEmpty:
                                        {
                                            message:'官方网站不能为空'
                                        }
                                }
                        }
                }
        }
    );
}
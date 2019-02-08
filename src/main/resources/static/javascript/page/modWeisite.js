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
            feedbackIcons:
                {
                    valid: 'glyphicon glyphicon-ok', //验证成功
                    invalid: 'glyphicon glyphicon-remove', //验证失败
                    validating: 'glyphicon glyphicon-refresh' //这个不能删 删了图标会显示不出来
                },

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
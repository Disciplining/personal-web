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
                    title:
                        {

                            validators:
                                {
                                    notEmpty:
                                        {
                                            message:'标题不能为空'
                                        },
                                    stringLength:
                                        {
                                            max : 16,
                                            message : '最多16个字符'
                                        }
                                }
                        },
                    description:
                        {

                            validators:
                                {
                                    notEmpty:
                                        {
                                            message:'描述不能为空'
                                        },
                                    stringLength:
                                        {
                                            max : 256,
                                            message : '最多256个字符'
                                        }
                                }
                        }
                }
        }
    );
}
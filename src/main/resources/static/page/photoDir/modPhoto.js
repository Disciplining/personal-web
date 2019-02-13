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
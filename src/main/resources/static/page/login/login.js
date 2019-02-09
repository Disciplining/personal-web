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
    $('#admin-login-form,#common-login-form').bootstrapValidator
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
                                            message:'请输入用户名'
                                        }
                                }
                        },
                    password:
                        {
                            validators:
                                {
                                    notEmpty:
                                        {
                                            message:'请输入密码'
                                        }
                                }
                        }
            }
        }
    );
}
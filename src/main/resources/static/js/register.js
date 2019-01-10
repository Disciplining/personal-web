$(  function ()
    {
        datePlugin();
        bootStrapVid();
        checkoutName();
    }
);

/**
 * 表单中的日期字段
 */
function datePlugin()
{
    $("#datetime").datetimepicker(
                                    {
                                        format: 'YYYY-MM-DD',
                                        locale: moment.locale('zh-cn')
                                    }
                                 );
}

/**
 * BootStrap表单验证
 */
function bootStrapVid()
{
    $('#register-form').bootstrapValidator
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
                    userName:
                        {

                            validators:
                                {
                                    notEmpty:
                                        {
                                            message:'用户名不能为空'
                                        },
                                    stringLength:
                                        {
                                            min : 3,
                                            max : 7,
                                            message : '用户名最少3个字符,最多7个字符'
                                        }
                                }
                        },
                    password:
                        {
                            validators:
                                {
                                    notEmpty:
                                        {
                                            message:'密码不能为空'
                                        },
                                    stringLength:
                                        {
                                            min : 4,
                                            max : 12,
                                            message:'密码最少4个字符,最多12个字符'
                                        }
                                }
                        },
                    repPassword:
                        {
                            validators:
                                {
                                    notEmpty:
                                        {
                                            message : '请再次输入密码'
                                        },
                                    stringLength:
                                        {
                                            min : 4,
                                            max : 12,
                                            message:'密码最少4个字符,最多12个字符'
                                        },
                                    identical:
                                        {
                                            field : 'password',
                                            message : '两次输入的密码不一致'
                                        }
                                }
                        },
                    email:
                        {
                            validators:
                                {
                                    emailAddress:
                                        {
                                            message : '邮箱格式不符合要求'
                                        }
                                }
                        }
            }
        }
    );
}

/*-----------------------------检查用户名是否已被注册-----------------------------*/
/**
 * 检查用户名是否已被注册
 */
function checkoutName()
{
    $('#userName').unbind('blur')
        .bind
        (
            'blur',
            function ()
            {
            }
        );
}

/**
 * 阻止提交按钮提交表单
 */
function preventSubmit()
{
    $('#submitData').unbind('click')
        .bind
        (
            'click',
            function (e)
            {
                e.preventDefault();
            }
        );
}

/**
 * 发送ajax查询
 */
function sendAjax()
{
    $.ajax
    (
        {
            url : '/ajaxTest',
            type : 'GET',
            dataType : 'text',
            success : function (respnse)
            {
            }
        }
    );
}
/*-----------------------------检查用户名是否已被注册结束-----------------------------*/
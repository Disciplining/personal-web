if ($('#status').text() == 'common')
{
    doWhatToCommon()
}
else if ($('#status').text() == 'admin')
{
    doWhatToAdmin()
}
else
{
    doWhatToCommon()
}

/*-----------------------两个表单的切换------------------------*/
/*   为“切换到管理员登录”绑定事件   */
$('#to-admin').unbind('click')
.bind
(
    'click',
    function()
    {
        doWhatToAdmin();
    }
)

/*   为“切换到普通用户登录”绑定事件   */
$('#to-common').unbind('click')
.bind
(
    'click',
    function()
    {
        doWhatToCommon();
    }
)

function doWhatToAdmin() //点击切换到管理员时要做的事情
{
    $('#hed-common').hide(); //隐藏普通标题
    $('#form-common').hide(); //隐藏普通表单
    $('#hed-admin').show(); //显示管理员
    $('#form-admin').show(); //显示管理员
}

function doWhatToCommon() //点击切换到普通用户时要做的事情
{
    $('#hed-common').show();
    $('#form-common').show();
    $('#hed-admin').hide();
    $('#form-admin').hide();
}
/*-----------------------------------------------------------*/
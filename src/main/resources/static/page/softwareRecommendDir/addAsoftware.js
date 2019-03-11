$(  function ()
    {
        prePic();
        bootStrapVid();
    }
);

/**
 * 图片预览
 */
function prePic()
{
    $('#pic').fileinput
    (
        {
            showUpload : false, //是否显示上传按钮,跟随文本框的那个

            showRemove : false, //显示移除按钮,跟随文本框的那个

            showCaption : true,//是否显示标题,就是那个文本框

            showPreview : true, //是否显示预览,不写默认为true

            dropZoneEnabled : false,//是否显示拖拽区域，默认不写为true，但是会占用很大区域

            //minImageWidth: 50, //图片的最小宽度

            //minImageHeight: 50,//图片的最小高度

            //maxImageWidth: 1000,//图片的最大宽度

            //maxImageHeight: 1000,//图片的最大高度

            //maxFileSize: 0, //文件最大大小，单位为kb，如果为0表示不限制文件大小

            //minFileCount: 0, //文件最小大学， 单位为kb，如果为0表示不限制文件大小

            maxFileCount : 1, //表示允许同时上传的最大文件个数

            enctype : 'multipart/form-data',

            validateInitialCount : true,

            previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",

            allowedFileTypes : [ 'image' ],//配置允许文件上传的类型

            allowedPreviewTypes : [ 'image' ],//配置所有的被预览文件类型

            allowedPreviewMimeTypes : [ 'jpg', 'png', 'gif' ],//控制被预览的所有mime类型

            language : 'zh'
        }
    )

    $('input.file-caption-name').attr('placeholder', '点击右方的选择图片按钮选择图片');
    $('span.hidden-xs').text("选择图片");
}

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
                    pic:
                        {

                            validators:
                                {
                                    notEmpty:
                                        {
                                            message:'软件图标不能为空'
                                        }
                                }
                        },
                    name:
                        {

                            validators:
                                {
                                    notEmpty:
                                        {
                                            message:'软件名不能为空'
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
                                            message:'软件介绍不能为空'
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
/**
 * Created by Administrator on 2018-01-30.
 */
/**
 1、首先用WebUploader.create创建一个 WebUploader对象 ，并在create中添加自定义配置项
 2、然后手动给WebUploader对象添加事件，用到的基本事件是
 fileQueued 文件被添加进队列的时候，在thelist div 中显示文件信息
 uploadProgress 文件上传过程中创建进度条实时显示
 uploadSuccess
 uploadError
 uploadComplete 在文件上传完后都会触发uploadComplete事件
 3、最后 调用upload()方法实现上传，
 */
$(function(){
    var uploader = WebUploader.create({
        pick: '#picker',
//        pick: {
//            id: '#picker',
//            label: '点击选择图片'
//        },
        // swf文件路径
        swf: '/webuploader-0.1.5/Uploader.swf',
        formData: {"dn": $("#requestDn").val()},//参数列表
        // 文件接收服务端。
        server: '/test/fileUpload',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,
        // 只允许选择图片文件。
//        accept: {
//            title: 'file',
//            extensions: 'cer'
//            //                mimeTypes: '.cer,'
//        }
        // 只允许选择图片文件。
//        accept: {
//            title: 'Images',
//            extensions: 'gif,jpg,jpeg,bmp,png',
//            mimeTypes: 'image/*'
//        }
    });
    var $list = $("#thelist");
    uploader.on('fileQueued', function (file) {
        console.log('fileQueued');
        $list.append('<div id="' + file.id + '" class="item">' +
            '<h4 class="info">' + file.name + '</h4>' +
            '<p class="state">等待上传...</p>' +
            '<p class="progress progress-bar">上传进度...</p>' +
            '</div>');
    });

    uploader.on('uploadSuccess', function (file) {
        console.log('uploadSuccess');
        $('#' + file.id).find('p.state').text('已上传');
    });
// 文件上传过程中创建进度条实时显示。
    uploader.on('uploadProgress', function (file, percentage) {
        console.log('uploadProgress');
        var $li = $('#' + file.id),
            $percent = $li.find('.progress .progress-bar');
        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<div class="progress progress-striped active">' +
                '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                '</div>' +
                '</div>').appendTo($li).find('.progress-bar');
        }
        $li.find('p.state').text('上传中');
        $percent.css('width', percentage * 100 + '%');
    });
    uploader.on('uploadError', function (file) {
        console.log('uploadError');
        $('#' + file.id).find('p.state').text('上传出错');
    });

    uploader.on('uploadComplete', function (file) {
        $('#' + file.id).find('.progress').fadeOut();
    });

    $("#ctlBtn").on('click', function () {
        uploader.upload();
    });
    $("#goBack").on('click', function () {
        $("#uploadFileDiv").empty();
        $("#uploadFile").removeClass("hidden");
    });
});

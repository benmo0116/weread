/**
 * Created by Administrator on 2018-01-30.
 */
$(function(){
    $('#downfile').click(function(){
        // location.href="/css/index.css";
        location.href="/test/download?path=/yc_edu.sql&fileName=ycedu.sql";
    });
    $('#downpic').click(function(){
        // ${ctxPath}/n/downloadFile/download?path=${teachRes.url}&fileName=${teachRes.upload_name}
        // location.href="/test/download?path=文件上传测试/&fileName=bg1.jpg";
        window.location.href="/test/download?path=/bg1.jpg&fileName=bg1.jpg";
    });
});
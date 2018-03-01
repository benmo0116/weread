/**
 * Created by Administrator on 2018-02-01.
 *  websocket
 */

var websocket = null;
//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
    var userid = 1;
    console.log("当前登录人角色id:" + userid);
    websocket = new WebSocket("ws://localhost:8080/websocket");
} else {
    alert('当前浏览器 Not support websocket');
}

//连接发生错误的回调方法
websocket.onerror = function () {
    setMessageInnerHTML("WebSocket连接发生错误");
};

//连接成功建立的回调方法
websocket.onopen = function () {
    setMessageInnerHTML("WebSocket连接成功");
}

//接收到消息的回调方法
websocket.onmessage = function (event) {
    setMessageInnerHTML(event.data);
    deskalert(event.data);
}

//连接关闭的回调方法
websocket.onclose = function () {
    setMessageInnerHTML("WebSocket连接关闭");
    layer.alert("您的通知连接已断开，若不是正常关闭，请刷新页面！(F5刷新页面)", {icon: 5, title: "提示"});
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    closeWebSocket();
}

//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
//        document.getElementById('showMessage').innerHTML += innerHTML + '<br/>';
    console.log(innerHTML);
}

//关闭WebSocket连接
function closeWebSocket() {
    websocket.close();
}

//发送消息
function send(message) {
//        var message = document.getElementById('text').value;
    console.log(message);
    var date = new Date();
    websocket.send(message + "\n" +getNow());
}
//显示桌面通知 Notification
function deskalert(message) {
    //请求权限 api 1:
    Notification.requestPermission().then(function (result) {
        // result可能是是granted, denied, 或default.
        console.log(result);
        if (result == "granted") {
            notify(message);
        } else {
            showAlert(message);
        }
    });
    //api 2:
//        Notification.requestPermission(function (permission) {
//            console.log(permission);
//            if (permission == "granted"){
//                notify(message);
//            }
//        });
}

function notify(message) {
    var notification = new Notification("有新消息", {
        //常用属性
        body: message,
        icon: '',
        dir: 'auto',
        silent: true
        //silent,sound属性 常见的浏览器尚并不支持 还需new一个Audio的js对象，然后使用这个audio的play方法，播放对应的wav文件。
//                sound:"${ctxPath}/audios/alert.wav"
    });
    //定时关闭
    setTimeout(function () {
        notification.close();//关闭桌面通知
    }, 2 * 60000);
    //四个相关事件
    notification.onclick = function () {
        //点击桌面通知，模拟实现把用户小化的窗口大化并刷新
//                notification.close();//关闭桌面通知
//                var href = window.location.href;//获取当前页面的url
//                window.close();//将当前页面关闭
//                window.open(href);//新开一个页面，url为当前页面
    }
    notification.onerror = function () {
        //当有错误发生时会onerror函数会被调用
        console.log('桌面通知发生了错误');
        //浏览器不支持或者为允许通知  弹窗
        showAlert(message);
    }
    notification.onshow = function () {
        //消息框显示时会被调用
        console.log('桌面通知显示ing');
        var music = new Audio("${ctxPath}/audios/2478.wav");
        music.play();
    }
    notification.onclose = function () {
        console.log('桌面通知关闭ing');
    }
}

//不重复叠加
function showAlert(message) {
    layer.alert(message, {icon: 7, title: "提示"});
}
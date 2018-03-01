(function($) {
    //时间戳和时间相互转换
    $.extend({
        myTime: {
            /**
             * 当前时间戳
             * @return <int>        unix时间戳(秒)
             */
            CurTime: function(){
                return Date.parse(new Date())/1000;
            },
            /**
             * 日期 转换为 Unix时间戳
             * @param <string> 2014-01-01 20:20:20  日期格式
             * @return <int>        unix时间戳(秒)
             */
            DateToUnix: function(string) {
                var f = string.split(' ', 2);
                var d = (f[0] ? f[0] : '').split('-', 3);
                var t = (f[1] ? f[1] : '').split(':', 3);
                return (new Date(
                        parseInt(d[0], 10) || null,
                        (parseInt(d[1], 10) || 1) - 1,
                        parseInt(d[2], 10) || null,
                        parseInt(t[0], 10) || null,
                        parseInt(t[1], 10) || null,
                        parseInt(t[2], 10) || null
                    )).getTime() / 1000;
            },
            /**
             * 时间戳转换日期
             * @param <int> unixTime    待时间戳(秒)  毫秒
             * @param <bool> isFull    返回完整时间(Y-m-d 或者 Y-m-d H:i:s)
             * @param <int>  timeZone   时区
             */
            UnixToDate: function(unixTime, isFull, timeZone) {
                if (typeof (timeZone) == 'number')
                {
                    unixTime = parseInt(unixTime) + parseInt(timeZone) * 60 * 60;
                }
                // console.log(unixTime);
                var time = unixTime.toString().length >10? new Date(unixTime): new Date(unixTime * 1000);
                // console.log(unixTime);
                var ymdhis = "";
                // ymdhis += time.getUTCFullYear() + "-";
                // ymdhis += (time.getUTCMonth()+1) + "-";
                // ymdhis += time.getUTCDate();
                ymdhis += time.getFullYear() + "-";
                ymdhis += ((time.getMonth()+1)<10? "0":"") +(time.getMonth()+1)+ "-";
                ymdhis += (time.getDate()<10? "0":"") + time.getDate();
                if (isFull === true)
                {
                    // ymdhis += " " + time.getUTCHours() + ":";
                    // ymdhis += time.getUTCMinutes() + ":";
                    // ymdhis += time.getUTCSeconds();
                    ymdhis += " " + (time.getHours() <10? "0":"") + time.getHours() + ":";
                    ymdhis += (time.getMinutes() <10? "0":"") + time.getMinutes() + ":";
                    ymdhis += (time.getSeconds() <10? "0":"") + time.getSeconds();
                }
                return ymdhis;
            }
        }
    });
})(jQuery);

//格式化当前时间   yyyy-mm-dd hh:MM:ss
function formatSingleNum(num){
    return num<10?"0"+num:""+num
}
function getNow() {
    var d = new Date();
    var timenow =d.getFullYear()+'-'+(d.getMonth()<9?"0":"")+ (d.getMonth()+1)+'-'+formatSingleNum(d.getDate())
        +' '+formatSingleNum(d.getHours())+':'+formatSingleNum(d.getMinutes())+':'+formatSingleNum(d.getSeconds());
    return timenow;
}

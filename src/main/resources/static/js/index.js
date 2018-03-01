var book_page = 1;
var booklist_page = 1;
var record_page = 1;
$(function () {
    $(".lastPage").click(function (e) {
        paging(-1, $(this).parent().attr("data-name"));
    });
    $(".nextPage").click(function (e) {
        paging(1, $(this).parent().attr("data-name"));
    });
    $.ajaxSetup({
        type: "POST",
        dataType: "json"
    });
});
// window.history.back(-1);返回上一页
// location.reload();页面刷新
// window.location.href = "/book/add";
//分页 模块名 第几页
function paging(i, modelename) {
    switch (modelename) {
        case 'book':
            var _page = (book_page + i) < 1 ? 1 : book_page + i;
            ajax("/book/list", _page, 12, function (data) {
                if (data.length != 0){
                    book_page = _page;
                    parseBooks(data);
                }
            });
            break;
        case 'booklist':
            var _page = (booklist_page + i) < 1 ? 1 : booklist_page + i;
            ajax("/booklist/list", _page, 7, function (data) {
                if (data.length != 0){
                    booklist_page = _page;
                    parseBookLists(data);
                }
            });
            break;
        case 'record':
            var _page = (record_page + i) < 1 ? 1 : record_page + i;
            ajax("/record/list", _page, 15, function (data) {
                if (data.length != 0){
                    record_page = _page;
                    parseRecords(data);
                }
            });
            break;
        default:
            break;
    }
}
// 另一种写法
// $.myajax = function(url, _page, callback) {
//     $.ajax({
//         url: url,
//         data: {pageNum:_page, pageSize:10},
//         success:callback
//     });
// }

function ajax(url, _page, pagesize, callback) {
    $.ajax({
        url: url,
        data: {pageNum: _page, pageSize: pagesize},
        success: callback
    });
}
function parseBooks(data) {
    var elem = $('#book');
    elem.html("");
    $.each(data, function (i, book) {
        var e = $("<li></li>").text(book.bookname);   // 以 jQuery 创建新元素
        elem.append(e);
    });
}
function parseBookLists(data) {
    var elem = $('#booklist');
    elem.html("");
    $.each(data, function (i, book) {
        var e = $("<li></li>").text(book.username + "-" + book.bookname);   // 以 jQuery 创建新元素
        elem.append(e);
    });
}
function parseRecords(data) {
    var elem = $('#record');
    elem.html("");
    var content = "";
    $.each(data, function (i, record) {
        content += "<tr th:inline='text'>";
        // content += "<td th:text='${#dates.format("+record.recordtime+", \'yyyy-MM-dd\')}'>" + record.recordtime + "</td>";
        content += "<td>" + parseTimestamp(record.recordtime) + "</td>";
        content += "<td>" + record.nickname + "</td>";
        content += "<td class=''>" + record.bookname + "</td>";
        content += "<td class='hidden-xs'>" + record.bookname2 + "</td>";
        content += "<td>" + record.author + "</td>";
        content += "<td class=''>" + record.country + "</td>";
        content += "<td>" + record.readpercent + "</td>";
        content += "</tr>";
    });
    // console.log(data);
    // console.log(content);
    elem.html(content);
}
function parseTimestamp(time) {
    return $.myTime.UnixToDate(time, true);
}



var lypApp = angular.module('lypApp', ['ui.bootstrap', 'remoteValidation', 'ngFileUpload']);

/**
 * 日期时间长整型（毫秒数）转换为日期（Date）
 */
lypApp.filter("longToDate", function(){
    return function(longTime) {
        if(longTime != null && longTime != undefined && !isNaN(longTime))
            return new Date().setTime(longTime);
        else
            return "";
    };
});

/**
 * 日期时间长整型（毫秒数）转换为日期（Date）
 */
lypApp.filter("reverse", function(){
    return function(items) {
        return items.slice().reverse();
    };
});
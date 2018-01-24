/**
 * 校验数字，只能是整数和小数
 * @param data
 */
function checkNumber(data) {
    var reg = /^([1-9]\d*|0)(\.\d{1,3})?$/;
    return reg.test(data);
}

/**
 * 检测输入的内容是否是数字或者小数
 */
function checkData(obj){
    var value = $(obj).val();
    if(!checkNumber(value)){
        $(obj).val($(obj).attr('data-value'));
        layer.tips("请输入整数或者小数,且小数点后只能保留3位",$(obj));
    }
}

/**
 * 验证数字，把非数字的字符替换成空
 * @param v
 */
function numberValidate(v){
    v.value=v.value.replace(/[^\d]/g,'');
}

/**
 * 生成密码
 * @returns {string}
 */
function randPassword() {
    var text = [ 'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ', '1234567890','!@#' ];
    var rand = function(min, max) {
        return Math.floor(Math.max(min, Math.random() * (max + 1)));
    }
    var len = 8;
    var pw = '';
    for (i = 0; i < len; ++i) {
        var strpos = rand(0, 3);
        pw += text[strpos].charAt(rand(0, text[strpos].length));
    }
    return pw;
}

/**
 * 日期转换，把long类型的日期转换成 yyyy-MM-dd HH:mm:ss
 * @param time
 * @returns {string}
 */
function covertDate (time){
    var date = new Date();
    date.setTime(time['time']);
    return date.getFullYear() + "-" + (date.getMonth()+1) + "-" + (date.getDate() <10 ? "0"+date.getDate() : date.getDate()) + " "
        + date.getHours() +":"+ date.getMinutes() + ":" + date.getSeconds();
}

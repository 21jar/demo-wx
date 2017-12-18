/**
 * Created by 胡超云 on 2016/12/8.
 */
var countdown = {
    node: null,
    count: 0,
    start:function(count){
        if(this.count > 0){
            this.node.html(this.count-- + "s后重新发送");

            var _this = this;
            setTimeout(function(){
                _this.start(count);
            },1000);
        }else{
            this.node.removeAttr("disabled");
            this.node.removeClass("btn-disabled");
            this.node.html("再次发送");
            this.count = count;
        }
    },
    //初始化
    init:function(node, count){
        this.node = node;
        this.count = count || 60;
        this.node.attr("disabled", true);
        this.node.addClass("btn-disabled");

        this.start(this.node);
    }
};
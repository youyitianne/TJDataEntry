(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-f577"],{"+hE8":function(r,e,t){},"41Be":function(r,e,t){"use strict";t.d(e,"a",function(){return s});var n=t("Q2AE");function s(r){if(r&&r instanceof Array&&r.length>0){var e=r;return!!(n.a.getters&&n.a.getters.roles).some(function(r){return e.includes(r)})}return!1}},AG6u:function(r,e,t){"use strict";var n=t("+hE8");t.n(n).a},X9Rj:function(r,e,t){"use strict";t.d(e,"a",function(){return s});var n=t("t3Un");function s(r){return Object(n.a)({url:"/updatePassword",method:"post",data:r})}},jPpK:function(r,e,t){"use strict";t.r(e);var n=t("QbLZ"),s=t.n(n),a=t("L2JU"),o=(t("X9Rj"),t("41Be")),i={created:function(){this.routerHandler()},watch:{password_two:{handler:function(){}}},data:function(){var r=this;return{pwdType:"password",rules2:{pass:[{validator:function(e,t,n){""===t?n(new Error("请输入密码")):(""!==r.ruleForm2.checkPass&&r.$refs.ruleForm2.validateField("checkPass"),n())},trigger:"blur"}],checkPass:[{validator:function(e,t,n){""===t?n(new Error("请再次输入密码")):t!==r.ruleForm2.pass?n(new Error("两次输入密码不一致!")):t.length<5?n(new Error("密码长度需要大于5位!")):n()},trigger:"blur"}]},ruleForm2:{pass:"",checkPass:""},pageLoading:!1}},components:{"remote-js":{render:function(r){return r("script",{attrs:{type:"text/javascript",src:this.src}})},props:{src:{type:String,required:!0}}}},name:"Dashboard",computed:s()({},Object(a.b)(["name","roles"])),methods:{checkPermission:o.a,routerHandler:function(){this.checkPermission(["planner"])?this.$router.push({path:"/homepage/addata"}):this.checkPermission(["admin","director"])?this.$router.push({path:"/homepage/earned"}):this.$router.push({path:"/homepage/productdata"})}}},u=(t("AG6u"),t("KHd+")),c=Object(u.a)(i,function(){var r=this.$createElement;return(this._self._c||r)("div",{staticClass:"dashboard-container"})},[],!1,null,"5e70af82",null);c.options.__file="route.vue";e.default=c.exports}}]);
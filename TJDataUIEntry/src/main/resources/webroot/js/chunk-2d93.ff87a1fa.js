(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-2d93"],{"0Ejn":function(t,e,a){"use strict";a.r(e);a("X4fA"),a("lwSp"),a("TiqC");var n=a("t3Un");var r={name:"DropzoneDemo",data:function(){return{pagesize:20,totalPages:0,tableData:[],hidtableData:[]}},mounted:function(){this.initTable()},methods:{initTable:function(){var t=this;Object(n.a)({url:"/api/system/listChannelPromoRecord",method:"get"}).then(function(e){3e3===e.repcode?(t.hidtableData=e.data,t.totalPages=e.data.length,t.pageChange(1)):t.$message({message:"表格初始化失败！",type:"warning"})}).catch(function(e){t.$message({message:"表格初始化失败！",type:"warning"})})},pageChange:function(t){console.log(this.currentpage),this.tableData=[];for(var e=20*(t-1);e<20*t&&!(e>=this.hidtableData.length);e++)this.tableData.push(this.hidtableData[e])}}},o=(a("fHEl"),a("KHd+")),i=Object(o.a)(r,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"components-container"},[a("el-card",{staticStyle:{margin:"20px"},attrs:{shadow:"always"}},[a("el-table",{staticStyle:{width:"100%"},attrs:{height:"780",border:"",data:t.tableData}},[a("el-table-column",{attrs:{width:"80",prop:"id",label:"ID"}}),t._v(" "),a("el-table-column",{attrs:{prop:"promoName",label:"应用名"}}),t._v(" "),a("el-table-column",{attrs:{prop:"channelPackage",label:"渠道包名"}}),t._v(" "),a("el-table-column",{attrs:{prop:"channelRule",label:"跳转规则"}}),t._v(" "),a("el-table-column",{attrs:{prop:"promoPackageName",label:"互推包名"}}),t._v(" "),a("el-table-column",{attrs:{prop:"promoIconUrl",label:"互推图片链接"}}),t._v(" "),a("el-table-column",{attrs:{width:"80",prop:"promoValue",label:"权重"}})],1),t._v(" "),a("el-pagination",{attrs:{"page-size":t.pagesize,layout:"prev, pager, next",total:t.totalPages},on:{"current-change":t.pageChange}})],1)],1)},[],!1,null,"c23170fc",null);i.options.__file="channelPromoRecord.vue";e.default=i.exports},TiqC:function(t,e,a){"use strict";a.d(e,"c",function(){return r}),a.d(e,"a",function(){return o}),a.d(e,"b",function(){return i});var n=a("t3Un");function r(){return Object(n.a)({url:"/api/system/getPromoChannel",method:"get"})}function o(t){return Object(n.a)({url:"/api/system/addPromoChannel",method:"post",data:t})}function i(t){return Object(n.a)({url:"/api/system/editPromoChannel",method:"patch",data:t})}},"e/sL":function(t,e,a){},fHEl:function(t,e,a){"use strict";var n=a("e/sL");a.n(n).a},lwSp:function(t,e,a){"use strict";a.d(e,"g",function(){return r}),a.d(e,"a",function(){return o}),a.d(e,"e",function(){return i}),a.d(e,"h",function(){return u}),a.d(e,"b",function(){return l}),a.d(e,"f",function(){return s}),a.d(e,"d",function(){return c}),a.d(e,"c",function(){return d}),a.d(e,"i",function(){return m}),a.d(e,"j",function(){return p}),a.d(e,"k",function(){return h});var n=a("t3Un");function r(){return Object(n.a)({url:"/api/system/getChannelPromoEdit",method:"get"})}function o(t){return Object(n.a)({url:"/api/system/addChannelPromoEdit",method:"post",data:t})}function i(t){return Object(n.a)({url:"/api/system/EditChannelPromoEdit",method:"patch",data:t})}function u(){return Object(n.a)({url:"/api/system/getPromoList",method:"get"})}function l(t){return Object(n.a)({url:"/api/system/addPromoList",method:"post",data:t})}function s(t){return Object(n.a)({url:"/api/system/editPromoList",method:"patch",data:t})}function c(t){return Object(n.a)({url:"/api/system/delPromoList",method:"delete",params:t})}function d(t){return Object(n.a)({url:"/api/system/addPromoListRecord",method:"post",data:t})}function m(t){return Object(n.a)({url:"/api/system/getRowPromoList",method:"get",params:t})}function p(){return Object(n.a)({url:"/qiniufile",method:"get"})}function h(t){return Object(n.a)({url:"/api/system/synchroPromoList",method:"post",data:t})}}}]);
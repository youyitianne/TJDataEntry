(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-28fe"],{"+ULj":function(e,n,t){"use strict";t.r(n);t("X4fA"),t("lwSp");var a=t("TiqC"),o={name:"DropzoneDemo",data:function(){return{currentpage:1,id:0,promoChannel:{},promoChannelState:"add",tableData:[],promoChannelDialogVisible:!1,pagesize:20,totalPages:0,hidTableData:[]}},mounted:function(){this.initTable()},methods:{checkPermission:t("41Be").a,addPromoChannel:function(){var e=this,n=this;this.$refs.promoChannel.validate(function(t){if(!t)return!1;var o={promoChannelName:e.promoChannel.promoChannelName,promoChannelPackage:e.promoChannel.promoChannelPackage,promoChannelMark:e.promoChannel.promoChannelMark,channelRule:e.promoChannel.channelRule};Object(a.a)(o).then(function(t){3e3===t.repcode?(n.$notify({title:"提示",message:"添加渠道成功！",type:"success"}),e.promoChannelDialogVisible=!1,e.initTable()):n.$notify({title:"提示",message:"添加渠道失败，请检查渠道标识是否重复！",type:"warning"})}).catch(function(e){console.error(e),n.$notify({title:"提示",message:"添加渠道失败！",type:"warning"})})})},editPromoChannel:function(){var e=this,n=this;this.$refs.promoChannel.validate(function(t){if(!t)return!1;var o={id:e.id,channelRule:e.promoChannel.channelRule,promoChannelName:e.promoChannel.promoChannelName,promoChannelPackage:e.promoChannel.promoChannelPackage,promoChannelMark:e.promoChannel.promoChannelMark};Object(a.b)(o).then(function(t){3e3===t.repcode?(n.$notify({title:"提示",message:"修改渠道成功！",type:"success"}),e.promoChannelDialogVisible=!1,e.initTable()):n.$notify({title:"提示",message:"修改渠道失败！",type:"warning"})}).catch(function(e){console.error(e),n.$notify({title:"提示",message:"修改渠道失败！",type:"warning"})})})},initTable:function(){var e=this,n=this;Object(a.c)().then(function(t){3e3===t.repcode?(e.totalPages=t.data.length,e.hidTableData=t.data,e.pageChange(e.currentpage)):n.$notify({title:"提示",message:"获取互推渠道列表失败！",type:"warning"})}).catch(function(e){console.error(e),n.$notify({title:"提示",message:"获取互推渠道列表失败！",type:"warning"})})},addPromoChannelBtu:function(){var e=this;this.promoChannel={},this.promoChannel.channelRule="market://details?id={0}",this.$nextTick(function(){e.$refs.promoChannel.clearValidate()}),this.promoChannelState="add",this.promoChannelDialogVisible=!0},editPromoChannelDialog:function(e){this.promoChannel={},this.id=e.id,this.$set(this.promoChannel,"channelRule",e.channelRule),this.$set(this.promoChannel,"promoChannelName",e.promoChannelName),this.$set(this.promoChannel,"promoChannelMark",e.promoChannelMark),this.$set(this.promoChannel,"promoChannelPackage",e.promoChannelPackage),this.promoChannelState="edit",this.promoChannelDialogVisible=!0},pageChange:function(e){this.currentpage=e,this.tableData=[];for(var n=20*(e-1);n<20*e&&!(n>=this.hidTableData.length);n++)this.tableData.push(this.hidTableData[n])}}},r=(t("n0XB"),t("KHd+")),l=Object(r.a)(o,function(){var e=this,n=e.$createElement,t=e._self._c||n;return t("div",{staticClass:"components-container"},[e.checkPermission(["admin","director"])?t("el-button",{staticStyle:{margin:"20px"},attrs:{type:"primary"},on:{click:e.addPromoChannelBtu}},[e._v("添加")]):e._e(),e._v(" "),t("el-card",{staticStyle:{margin:"20px","margin-top":"-10px"},attrs:{shadow:"always"}},[t("el-table",{staticStyle:{width:"100%"},attrs:{height:"700",border:"",data:e.tableData}},[t("el-table-column",{attrs:{label:"序号",type:"index",width:"50"}}),e._v(" "),t("el-table-column",{attrs:{prop:"promoChannelName",label:"渠道名"}}),e._v(" "),t("el-table-column",{attrs:{prop:"promoChannelMark",label:"渠道标识"}}),e._v(" "),t("el-table-column",{attrs:{prop:"promoChannelPackage",label:"渠道包名"}}),e._v(" "),t("el-table-column",{attrs:{prop:"channelRule",label:"跳转规则"}}),e._v(" "),e.checkPermission(["admin","director"])?t("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(n){return[t("el-button",{attrs:{type:"text"},on:{click:function(t){e.editPromoChannelDialog(n.row)}}},[e._v("编辑")])]}}])}):e._e()],1),e._v(" "),t("el-pagination",{attrs:{"page-size":e.pagesize,layout:"prev, pager, next",total:e.totalPages,"current-page":e.currentpage},on:{"current-change":e.pageChange}})],1),e._v(" "),t("el-dialog",{staticStyle:{height:"600px"},attrs:{width:"700px",title:"互推渠道",visible:e.promoChannelDialogVisible},on:{"update:visible":function(n){e.promoChannelDialogVisible=n}}},[t("el-form",{ref:"promoChannel",staticStyle:{"margin-left":"50px"},attrs:{model:e.promoChannel,"label-position":"left","status-icon":""}},[t("el-form-item",{attrs:{label:"渠道名",rules:[{required:!0,message:"渠道名不能为空"}],prop:"promoChannelName","label-width":"120px"}},[t("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~"},model:{value:e.promoChannel.promoChannelName,callback:function(n){e.$set(e.promoChannel,"promoChannelName",n)},expression:"promoChannel.promoChannelName"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"渠道标识",rules:[{required:!0,message:"渠道标识不能为空"}],prop:"promoChannelMark","label-width":"120px"}},[t("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~",disabled:"edit"===e.promoChannelState},model:{value:e.promoChannel.promoChannelMark,callback:function(n){e.$set(e.promoChannel,"promoChannelMark",n)},expression:"promoChannel.promoChannelMark"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"渠道包名",rules:[{required:!0,message:"渠道包名不能为空"}],prop:"promoChannelPackage","label-width":"120px"}},[t("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~"},model:{value:e.promoChannel.promoChannelPackage,callback:function(n){e.$set(e.promoChannel,"promoChannelPackage",n)},expression:"promoChannel.promoChannelPackage"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"跳转规则",rules:[{required:!0,message:"跳转规则不能为空"}],prop:"channelRule","label-width":"120px"}},[t("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~"},model:{value:e.promoChannel.channelRule,callback:function(n){e.$set(e.promoChannel,"channelRule",n)},expression:"promoChannel.channelRule"}})],1)],1),e._v(" "),t("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(n){e.promoChannelDialogVisible=!1}}},[e._v(e._s("取消"))]),e._v(" "),"add"===e.promoChannelState?t("el-button",{attrs:{type:"primary"},on:{click:e.addPromoChannel}},[e._v(e._s("添加"))]):e._e(),e._v(" "),"edit"===e.promoChannelState?t("el-button",{attrs:{type:"primary"},on:{click:e.editPromoChannel}},[e._v(e._s("修改"))]):e._e()],1)],1)],1)},[],!1,null,"b1355596",null);l.options.__file="promoChannel.vue";n.default=l.exports},"41Be":function(e,n,t){"use strict";t.d(n,"a",function(){return o});var a=t("Q2AE");function o(e){if(e&&e instanceof Array&&e.length>0){var n=e;return!!(a.a.getters&&a.a.getters.roles).some(function(e){return n.includes(e)})}return!1}},TiqC:function(e,n,t){"use strict";t.d(n,"c",function(){return o}),t.d(n,"a",function(){return r}),t.d(n,"b",function(){return l});var a=t("t3Un");function o(){return Object(a.a)({url:"/api/system/getPromoChannel",method:"get"})}function r(e){return Object(a.a)({url:"/api/system/addPromoChannel",method:"post",data:e})}function l(e){return Object(a.a)({url:"/api/system/editPromoChannel",method:"patch",data:e})}},lwSp:function(e,n,t){"use strict";t.d(n,"g",function(){return o}),t.d(n,"a",function(){return r}),t.d(n,"e",function(){return l}),t.d(n,"h",function(){return i}),t.d(n,"b",function(){return s}),t.d(n,"f",function(){return c}),t.d(n,"d",function(){return h}),t.d(n,"c",function(){return p}),t.d(n,"i",function(){return m}),t.d(n,"j",function(){return u}),t.d(n,"k",function(){return d});var a=t("t3Un");function o(){return Object(a.a)({url:"/api/system/getChannelPromoEdit",method:"get"})}function r(e){return Object(a.a)({url:"/api/system/addChannelPromoEdit",method:"post",data:e})}function l(e){return Object(a.a)({url:"/api/system/EditChannelPromoEdit",method:"patch",data:e})}function i(){return Object(a.a)({url:"/api/system/getPromoList",method:"get"})}function s(e){return Object(a.a)({url:"/api/system/addPromoList",method:"post",data:e})}function c(e){return Object(a.a)({url:"/api/system/editPromoList",method:"patch",data:e})}function h(e){return Object(a.a)({url:"/api/system/delPromoList",method:"delete",params:e})}function p(e){return Object(a.a)({url:"/api/system/addPromoListRecord",method:"post",data:e})}function m(e){return Object(a.a)({url:"/api/system/getRowPromoList",method:"get",params:e})}function u(){return Object(a.a)({url:"/qiniufile",method:"get"})}function d(e){return Object(a.a)({url:"/api/system/synchroPromoList",method:"post",data:e})}},n0XB:function(e,n,t){"use strict";var a=t("yn/L");t.n(a).a},"yn/L":function(e,n,t){}}]);
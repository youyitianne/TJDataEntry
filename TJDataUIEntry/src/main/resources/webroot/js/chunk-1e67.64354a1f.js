(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-1e67"],{"+ULj":function(e,t,n){"use strict";n.r(t);n("X4fA"),n("lwSp");var o=n("TiqC"),a={name:"DropzoneDemo",data:function(){return{id:0,promoChannel:{},promoChannelState:"add",tableData:[],promoChannelDialogVisible:!1,pagesize:20,totalPages:0}},mounted:function(){this.initTable()},methods:{addPromoChannel:function(){var e=this,t=this;this.$refs.promoChannel.validate(function(n){if(!n)return!1;var a={promoChannelName:e.promoChannel.promoChannelName,promoChannelPackage:e.promoChannel.promoChannelPackage,promoChannelMark:e.promoChannel.promoChannelMark};Object(o.a)(a).then(function(n){3e3===n.repcode?(t.$notify({title:"提示",message:"添加渠道成功！",type:"success"}),e.promoChannelDialogVisible=!1,e.initTable()):t.$notify({title:"提示",message:"添加渠道失败！",type:"warning"})}).catch(function(e){console.error(e),t.$notify({title:"提示",message:"添加渠道失败！",type:"warning"})})})},editPromoChannel:function(){var e=this,t=this;this.$refs.promoChannel.validate(function(n){if(!n)return!1;var a={id:e.id,promoChannelName:e.promoChannel.promoChannelName,promoChannelPackage:e.promoChannel.promoChannelPackage,promoChannelMark:e.promoChannel.promoChannelMark};Object(o.b)(a).then(function(n){3e3===n.repcode?(t.$notify({title:"提示",message:"修改渠道成功！",type:"success"}),e.promoChannelDialogVisible=!1,e.initTable()):t.$notify({title:"提示",message:"修改渠道失败！",type:"warning"})}).catch(function(e){console.error(e),t.$notify({title:"提示",message:"修改渠道失败！",type:"warning"})})})},initTable:function(){var e=this,t=this;Object(o.c)().then(function(n){3e3===n.repcode?e.tableData=n.data:t.$notify({title:"提示",message:"获取互推渠道列表失败！",type:"warning"})}).catch(function(e){console.error(e),t.$notify({title:"提示",message:"获取互推渠道列表失败！",type:"warning"})})},addPromoChannelBtu:function(){var e=this;this.promoChannel={},this.$nextTick(function(){e.$refs.promoChannel.clearValidate()}),this.promoChannelState="add",this.promoChannelDialogVisible=!0},editPromoChannelDialog:function(e){this.promoChannel={},this.id=e.id,this.$set(this.promoChannel,"promoChannelName",e.promoChannelName),this.$set(this.promoChannel,"promoChannelMark",e.promoChannelMark),this.$set(this.promoChannel,"promoChannelPackage",e.promoChannelPackage),this.promoChannelState="edit",this.promoChannelDialogVisible=!0}}},r=(n("LbXn"),n("KHd+")),i=Object(r.a)(a,function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"components-container"},[n("el-button",{staticStyle:{margin:"20px"},attrs:{type:"primary"},on:{click:e.addPromoChannelBtu}},[e._v("添加")]),e._v(" "),n("el-card",{staticStyle:{margin:"20px","margin-top":"-10px"},attrs:{shadow:"always"}},[n("el-table",{staticStyle:{width:"100%"},attrs:{height:"700",border:"",data:e.tableData}},[n("el-table-column",{attrs:{label:"序号",type:"index",width:"50"}}),e._v(" "),n("el-table-column",{attrs:{prop:"promoChannelName",label:"渠道名"}}),e._v(" "),n("el-table-column",{attrs:{prop:"promoChannelMark",label:"渠道标识"}}),e._v(" "),n("el-table-column",{attrs:{prop:"promoChannelPackage",label:"渠道包名"}}),e._v(" "),n("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-button",{attrs:{type:"text"},on:{click:function(n){e.editPromoChannelDialog(t.row)}}},[e._v("编辑")])]}}])})],1),e._v(" "),n("el-pagination",{attrs:{"page-size":e.pagesize,layout:"prev, pager, next",total:e.totalPages},on:{"current-change":function(e){}}})],1),e._v(" "),n("el-dialog",{staticStyle:{height:"600px"},attrs:{width:"700px",title:"互推渠道",visible:e.promoChannelDialogVisible},on:{"update:visible":function(t){e.promoChannelDialogVisible=t}}},[n("el-form",{ref:"promoChannel",staticStyle:{"margin-left":"50px"},attrs:{model:e.promoChannel,"label-position":"left","status-icon":""}},[n("el-form-item",{attrs:{label:"渠道名",rules:[{required:!0,message:"渠道名不能为空"}],prop:"promoChannelName","label-width":"120px"}},[n("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~"},model:{value:e.promoChannel.promoChannelName,callback:function(t){e.$set(e.promoChannel,"promoChannelName",t)},expression:"promoChannel.promoChannelName"}})],1),e._v(" "),n("el-form-item",{attrs:{label:"渠道标识",rules:[{required:!0,message:"渠道标识不能为空"}],prop:"promoChannelMark","label-width":"120px"}},[n("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~"},model:{value:e.promoChannel.promoChannelMark,callback:function(t){e.$set(e.promoChannel,"promoChannelMark",t)},expression:"promoChannel.promoChannelMark"}})],1),e._v(" "),n("el-form-item",{attrs:{label:"渠道包名",rules:[{required:!0,message:"渠道包名不能为空"}],prop:"promoChannelPackage","label-width":"120px"}},[n("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~"},model:{value:e.promoChannel.promoChannelPackage,callback:function(t){e.$set(e.promoChannel,"promoChannelPackage",t)},expression:"promoChannel.promoChannelPackage"}})],1)],1),e._v(" "),n("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{on:{click:function(t){e.promoEditVisible=!1}}},[e._v(e._s("取消"))]),e._v(" "),"add"===e.promoChannelState?n("el-button",{attrs:{type:"primary"},on:{click:e.addPromoChannel}},[e._v(e._s("添加"))]):e._e(),e._v(" "),"edit"===e.promoChannelState?n("el-button",{attrs:{type:"primary"},on:{click:e.editPromoChannel}},[e._v(e._s("修改"))]):e._e()],1)],1)],1)},[],!1,null,"60e6e831",null);i.options.__file="promoChannel.vue";t.default=i.exports},EYjk:function(e,t,n){},LbXn:function(e,t,n){"use strict";var o=n("EYjk");n.n(o).a},TiqC:function(e,t,n){"use strict";n.d(t,"c",function(){return a}),n.d(t,"a",function(){return r}),n.d(t,"b",function(){return i});var o=n("t3Un");function a(){return Object(o.a)({url:"/api/system/getPromoChannel",method:"get"})}function r(e){return Object(o.a)({url:"/api/system/addPromoChannel",method:"post",data:e})}function i(e){return Object(o.a)({url:"/api/system/editPromoChannel",method:"patch",data:e})}},lwSp:function(e,t,n){"use strict";n.d(t,"g",function(){return a}),n.d(t,"a",function(){return r}),n.d(t,"e",function(){return i}),n.d(t,"h",function(){return l}),n.d(t,"b",function(){return s}),n.d(t,"f",function(){return m}),n.d(t,"d",function(){return p}),n.d(t,"c",function(){return c}),n.d(t,"i",function(){return h}),n.d(t,"j",function(){return u}),n.d(t,"k",function(){return d});var o=n("t3Un");function a(){return Object(o.a)({url:"/api/system/getChannelPromoEdit",method:"get"})}function r(e){return Object(o.a)({url:"/api/system/addChannelPromoEdit",method:"post",data:e})}function i(e){return Object(o.a)({url:"/api/system/EditChannelPromoEdit",method:"patch",data:e})}function l(){return Object(o.a)({url:"/api/system/getPromoList",method:"get"})}function s(e){return Object(o.a)({url:"/api/system/addPromoList",method:"post",data:e})}function m(e){return Object(o.a)({url:"/api/system/editPromoList",method:"patch",data:e})}function p(e){return Object(o.a)({url:"/api/system/delPromoList",method:"delete",params:e})}function c(e){return Object(o.a)({url:"/api/system/addPromoListRecord",method:"post",data:e})}function h(e){return Object(o.a)({url:"/api/system/getRowPromoList",method:"get",params:e})}function u(){return Object(o.a)({url:"/qiniufile",method:"get"})}function d(e){return Object(o.a)({url:"/api/system/synchroPromoList",method:"post",data:e})}}}]);
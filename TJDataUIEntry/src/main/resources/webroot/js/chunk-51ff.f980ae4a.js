(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-51ff"],{Do8e:function(t,e,i){"use strict";var a=i("y3Ao");i.n(a).a},lwSp:function(t,e,i){"use strict";i.d(e,"g",function(){return n}),i.d(e,"a",function(){return o}),i.d(e,"e",function(){return r}),i.d(e,"h",function(){return l}),i.d(e,"b",function(){return u}),i.d(e,"f",function(){return s}),i.d(e,"d",function(){return c}),i.d(e,"c",function(){return d}),i.d(e,"i",function(){return p}),i.d(e,"j",function(){return f}),i.d(e,"k",function(){return h});var a=i("t3Un");function n(){return Object(a.a)({url:"/api/system/getChannelPromoEdit",method:"get"})}function o(t){return Object(a.a)({url:"/api/system/addChannelPromoEdit",method:"post",data:t})}function r(t){return Object(a.a)({url:"/api/system/EditChannelPromoEdit",method:"patch",data:t})}function l(){return Object(a.a)({url:"/api/system/getPromoList",method:"get"})}function u(t){return Object(a.a)({url:"/api/system/addPromoList",method:"post",data:t})}function s(t){return Object(a.a)({url:"/api/system/editPromoList",method:"patch",data:t})}function c(t){return Object(a.a)({url:"/api/system/delPromoList",method:"delete",params:t})}function d(t){return Object(a.a)({url:"/api/system/addPromoListRecord",method:"post",data:t})}function p(t){return Object(a.a)({url:"/api/system/getRowPromoList",method:"get",params:t})}function f(){return Object(a.a)({url:"/qiniufile",method:"get"})}function h(t){return Object(a.a)({url:"/api/system/synchroPromoList",method:"post",data:t})}},mA2W:function(t,e,i){"use strict";i.r(e);i("X4fA");var a=i("lwSp"),n=i("t3Un");var o={name:"DropzoneDemo",data:function(){return{currentpage:1,dialogVisible:!1,uploadQiniuUrl:"http://192.168.1.144:8091/qiniufile",respath:"http://192.168.1.144:8091/file?path=",tableData:[],hidtableData:[],fodderDialogVisible:!1,fileList:[],totalPages:0,pagesize:20,previewDialogVisible:!1,picture:"",dialogImageUrl:""}},mounted:function(){this.initTable()},methods:{delRecord:function(t){var e=this,i={id:t.qiniu_file_guid};this.$confirm("确认删除？").then(function(t){(function(t){return Object(n.a)({url:"/api/system/delQiniuFileRecord",method:"delete",params:t})})(i).then(function(t){3e3===t.repcode?(e.$message.success("删除本地记录成功！"),e.initTable()):e.$message.error("删除本地记录失败！")}).catch(function(t){console.error(t),e.$message.error("删除本地记录失败！")})}).catch(function(t){return!1})},uploadSuccess1:function(t,e,i){console.log(t)},uploadFail1:function(t,e,i){console.log(t)},submitUpload:function(){this.$refs.upload.submit()},handlePictureCardPreview:function(t){this.dialogImageUrl=t.url,this.dialogVisible=!0},bigPicture:function(t){this.previewDialogVisible=!0,this.picture=t},initTable:function(){var t=this;console.log(this.currentpage),Object(a.j)().then(function(e){3e3==e.repcode?(t.hidtableData=e.data,t.totalPages=e.data.length,t.pageChange(t.currentpage)):t.$message.error("表格初始化失败！")}).catch(function(t){console.error(t)})},pageChange:function(t){console.log(this.currentpage),this.currentpage=t,this.tableData=[];for(var e=20*(t-1);e<20*t&&!(e>=this.hidtableData.length);e++)this.tableData.push(this.hidtableData[e])}}},r=(i("Do8e"),i("KHd+")),l=Object(r.a)(o,function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"components-container"},[i("el-button",{staticStyle:{margin:"20px"},attrs:{type:"primary"},on:{click:function(e){t.fodderDialogVisible=!0}}},[t._v("添加")]),t._v(" "),i("el-card",{staticStyle:{margin:"20px","margin-top":"-10px"},attrs:{shadow:"always"}},[i("el-table",{staticStyle:{width:"100%"},attrs:{height:"700",border:"",data:t.tableData}},[i("el-table-column",{attrs:{label:"序号",type:"index",width:"50"}}),t._v(" "),i("el-table-column",{attrs:{prop:"qiniu_file_guid",label:"GUID"}}),t._v(" "),i("el-table-column",{attrs:{prop:"qiniu_file_name",label:"文件名"}}),t._v(" "),i("el-table-column",{attrs:{width:"600px",prop:"qiniu_file_path",label:"链接"}}),t._v(" "),i("el-table-column",{attrs:{label:"预览"},scopedSlots:t._u([{key:"default",fn:function(e){return[i("span",{staticStyle:{width:"100%"},on:{click:function(i){t.bigPicture(e.row.qiniu_file_path)}}},[i("img",{attrs:{src:e.row.qiniu_file_path,"min-width":"70",height:"70"}})])]}}])}),t._v(" "),i("el-table-column",{attrs:{label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[i("el-button",{staticStyle:{margin:"20px"},on:{click:function(i){t.delRecord(e.row)}}},[t._v("删除")])]}}])})],1),t._v(" "),i("el-pagination",{attrs:{"page-size":t.pagesize,layout:"prev, pager, next",total:t.totalPages,"current-page":t.currentpage},on:{"current-change":t.pageChange}})],1),t._v(" "),i("el-dialog",{attrs:{title:"大图预览",visible:t.previewDialogVisible},on:{"update:visible":function(e){t.previewDialogVisible=e}}},[i("img",{attrs:{src:t.picture}})]),t._v(" "),i("el-dialog",{staticStyle:{height:"600px"},attrs:{title:"素材上传",visible:t.fodderDialogVisible},on:{"update:visible":function(e){t.fodderDialogVisible=e}}},[i("el-upload",{ref:"upload",attrs:{action:t.uploadQiniuUrl,"list-type":"picture-card","auto-upload":!1,"on-success":t.uploadSuccess1,"on-error":t.uploadFail1,"on-preview":t.handlePictureCardPreview}},[i("i",{staticClass:"el-icon-plus"})]),t._v(" "),i("el-dialog",{attrs:{visible:t.dialogVisible},on:{"update:visible":function(e){t.dialogVisible=e}}},[i("img",{attrs:{width:"100%",src:t.dialogImageUrl,alt:""}})]),t._v(" "),i("el-button",{staticClass:"buttonupload",staticStyle:{margin:"20px"},attrs:{type:"primary"},on:{click:t.submitUpload}},[t._v("上传图片")])],1)],1)},[],!1,null,"6664f356",null);l.options.__file="channelPromoFodder.vue";e.default=l.exports},y3Ao:function(t,e,i){}}]);
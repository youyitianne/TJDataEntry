(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-22ad"],{"caD/":function(t,e,a){},cmJo:function(t,e,a){"use strict";var i=a("caD/");a.n(i).a},lwSp:function(t,e,a){"use strict";a.d(e,"b",function(){return l}),a.d(e,"a",function(){return o});var i=a("t3Un");function l(t){return Object(i.a)({url:"/api/system/addfodderinfo",method:"post",data:t})}function o(){return Object(i.a)({url:"/api/system/listfodderinfo",method:"get"})}},mA2W:function(t,e,a){"use strict";a.r(e);a("X4fA");var i=a("lwSp"),l={name:"DropzoneDemo",data:function(){return{respath:"http://192.168.1.144:8091/file?path=",tableData:[],fodderDialogVisible:!1,fileList:[],totalPages:999,pagesize:20,previewDialogVisible:!1,picture:""}},mounted:function(){this.initTable()},methods:{bigPicture:function(t){this.previewDialogVisible=!0,this.picture=t},initTable:function(){var t=this;Object(i.a)().then(function(e){if(3e3==e.repcode){t.tableData=e.data,t.totalPages=t.tableData.length;for(var a=0;a<t.tableData.length;a++)t.tableData[a].path1=t.respath+t.tableData[a].fodderguid}else t.$message.error("表格初始化失败！")}).catch(function(t){console.error(t)})},uploadSuccess:function(t){var e=this,a={fodderName:t.data.file,guid:t.data.guid};Object(i.b)(a).then(function(t){3e3==t.repcode?(e.$message({message:"添加成功！",type:"success"}),e.initTable()):e.$message.error("添加失败！")}).catch(function(t){console.error(t)})},pageChange:function(){}}},o=(a("cmJo"),a("KHd+")),n=Object(o.a)(l,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"components-container"},[a("el-button",{staticStyle:{margin:"20px"},attrs:{type:"primary"},on:{click:function(e){t.fodderDialogVisible=!0}}},[t._v("添加")]),t._v(" "),a("el-card",{staticStyle:{margin:"20px","margin-top":"-10px"},attrs:{shadow:"always"}},[a("el-table",{staticStyle:{width:"100%"},attrs:{height:"700",border:"",data:t.tableData}},[a("el-table-column",{attrs:{label:"序号",type:"index",width:"50"}}),t._v(" "),a("el-table-column",{attrs:{prop:"fodderguid",label:"GUID"}}),t._v(" "),a("el-table-column",{attrs:{prop:"fodderName",label:"文件名"}}),t._v(" "),a("el-table-column",{attrs:{prop:"path1",label:"链接"}}),t._v(" "),a("el-table-column",{attrs:{label:"预览"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{staticStyle:{width:"100%"},on:{click:function(a){t.bigPicture(e.row.path1)}}},[a("img",{attrs:{src:e.row.path1,"min-width":"70",height:"70"}})])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"text"},on:{click:function(a){t.handleDelete(e.row)}}},[t._v("上传")])]}}])})],1),t._v(" "),a("el-pagination",{attrs:{"page-size":t.pagesize,layout:"prev, pager, next",total:t.totalPages},on:{"current-change":t.pageChange}})],1),t._v(" "),a("el-dialog",{attrs:{title:"大图预览",visible:t.previewDialogVisible},on:{"update:visible":function(e){t.previewDialogVisible=e}}},[a("img",{attrs:{src:t.picture}})]),t._v(" "),a("el-dialog",{staticStyle:{height:"600px"},attrs:{title:"素材上传",visible:t.fodderDialogVisible},on:{"update:visible":function(e){t.fodderDialogVisible=e}}},[a("el-upload",{staticClass:"upload-demo",attrs:{action:"http://192.168.1.144:8091/file","file-list":t.fileList,"on-success":t.uploadSuccess,"list-type":"picture"}},[a("el-button",{attrs:{size:"small",type:"primary"}},[t._v("点击上传")]),t._v(" "),a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[t._v("只能上传jpg/png文件，且不超过500kb")])],1)],1)],1)},[],!1,null,"4b53aadb",null);n.options.__file="channelPromoFodder.vue";e.default=n.exports}}]);
(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-20d0"],{"41Be":function(e,t,o){"use strict";o.d(t,"a",function(){return i});var a=o("Q2AE");function i(e){if(e&&e instanceof Array&&e.length>0){var t=e;return!!(a.a.getters&&a.a.getters.roles).some(function(e){return t.includes(e)})}return!1}},"42j2":function(e,t,o){},DK83:function(e,t,o){"use strict";var a=o("42j2");o.n(a).a},TiqC:function(e,t,o){"use strict";o.d(t,"c",function(){return i}),o.d(t,"a",function(){return n}),o.d(t,"b",function(){return r});var a=o("t3Un");function i(){return Object(a.a)({url:"/api/system/getPromoChannel",method:"get"})}function n(e){return Object(a.a)({url:"/api/system/addPromoChannel",method:"post",data:e})}function r(e){return Object(a.a)({url:"/api/system/editPromoChannel",method:"patch",data:e})}},Urp3:function(e,t,o){"use strict";o.r(t);o("X4fA");var a=o("YXBG"),i=o("lwSp"),n=o("TiqC"),r={name:"DropzoneDemo",data:function(){return{switchDisabled:!1,appchannel:"",switchValue:!0,currentPromoListVisible:!1,uid:0,id:0,promoListState:"add",promoListIndex:0,editListState:"add",promo:{},innerVisible:!1,editState:"add",channelPromoEditList:[],respath:"http://192.168.1.144:8091/file?path=",fodderList:[],dataForm:{},channelPromoEdit:{},EditTable:[],promoListVisible:!1,promoEditVisible:!1,def_currentpage:1,search_currentpage:1,testModeLoading:!1,search_totalPages:0,def_paging:!0,search_paging:!1,searchDialog:!1,pageSize:20,totalPages:0,fetchForm:{appName:"",uid:"",packageName:""},testModeForm:{id:0,uid:"",appName:"",packageName:"",channel:"",mode:"",ad:""},tableData:[],promoChannelList:[],promoChannelListRel:[]}},mounted:function(){this.initTable(),this.initFodder(),this.initPromoChannel(),this.initPromoEdit(),this.initPromoList()},methods:{initPromoList:function(){var e=this,t=this;Object(i.h)().then(function(o){3e3===o.repcode?(console.log("互推渠道列表关联",o.data),e.promoChannelListRel=o.data):t.$notify({title:"提示",message:"互推渠道列表关联初始化失败",type:"warning"})}).catch(function(e){console.error(e),t.$notify({title:"提示",message:"互推渠道列表关联初始化失败",type:"warning"})})},initPromoChannel:function(){var e=this,t=this;Object(n.c)().then(function(o){3e3===o.repcode?(console.log("互推渠道列表",o.data),e.promoChannelList=o.data):t.$notify({title:"提示",message:"互推渠道列表初始化失败",type:"warning"})}).catch(function(e){console.error(e),t.$notify({title:"提示",message:"互推渠道列表初始化失败",type:"warning"})})},addPromoList:function(){var e=this,t=this,o={table:this.EditTable};Object(i.c)(o).then(function(o){var i=!0;if(3e3===o.repcode){i=!1,console.log("发布记录id",o.data);for(var n=o.data,r=[],l=0;l<e.EditTable.length;l++){var s={promoName:e.EditTable[l].promoName,channelPackage:e.EditTable[l].channelPackage,channelRule:e.EditTable[l].channelRule,promoPackageName:e.EditTable[l].promoPackageName,promoIconUrl:e.EditTable[l].promoIconUrl,promoValue:e.EditTable[l].promoValue,id:n[n.length-1-l].id.toString()};r.push(s)}var c={appId:e.uid,system:"channelPromo",status:r};console.log("提交的推广列表",c),Object(a.a)(c).then(function(o){var a=!0;3e3===o.repcode&&(200===JSON.parse(o.data).code&&(t.$notify({title:"提示",message:"添加推广列表成功",type:"success"}),e.promoListVisible=!1,a=!1));a&&t.$notify({title:"提示",message:"添加推广列表失败！",type:"warning"})}).catch(function(e){console.error(e),t.$notify({title:"提示",message:"添加推广列表失败！",type:"warning"})})}i&&t.$notify({title:"提示",message:"添加到互推发布记录失败",type:"warning"})}).catch(function(e){console.error(e),t.$notify({title:"提示",message:"添加到互推发布记录失败",type:"warning"})})},editPromoList:function(e){var t=this,o=this,n={table:this.EditTable};Object(i.c)(n).then(function(i){var n=!0;if(3e3===i.repcode){n=!1,console.log("发布记录id",i.data);for(var r=i.data,l=[],s=0;s<t.EditTable.length;s++){var c={promoName:t.EditTable[s].promoName,channelPackage:t.EditTable[s].channelPackage,channelRule:t.EditTable[s].channelRule,promoPackageName:t.EditTable[s].promoPackageName,promoIconUrl:t.EditTable[s].promoIconUrl,promoValue:t.EditTable[s].promoValue,id:r[r.length-1-s].id.toString()};l.push(c)}var m={appId:t.id,system:"channelPromo",status:l};console.log("提交的推广列表",m),Object(a.c)(m).then(function(a){var i=!0;3e3===a.repcode&&(200===JSON.parse(a.data).code&&(o.$notify({title:"提示",message:"编辑推广列表成功",type:"success"}),e||(t.promoListVisible=!1),i=!1));i&&o.$notify({title:"提示",message:"编辑推广列表失败！",type:"warning"})}).catch(function(e){console.error(e),o.$notify({title:"提示",message:"编辑推广列表失败！",type:"warning"})})}n&&o.$notify({title:"提示",message:"添加到互推发布记录失败",type:"warning"})}).catch(function(e){console.error(e),o.$notify({title:"提示",message:"添加到互推发布记录失败",type:"warning"})})},EditRow:function(e,t){var o=this;console.log(e),this.promo={},this.$nextTick(function(){o.$refs.promo.clearValidate()}),this.promoListIndex=t,this.promo.id=e.id;for(var a=0;a<this.channelPromoEditList.length;a++)if(this.channelPromoEditList[a].uid===e.uid){this.promo.promoEdit=this.channelPromoEditList[a];break}for(var i=0;i<this.fodderList.length;i++)if(this.fodderList[i].qiniu_file_guid===e.puid){this.promo.fodder=this.fodderList[i];break}this.promo.channelPackage=e.channelPackage,this.promo.channelRule=e.channelRule,this.promo.promoIconUrl=e.promoIconUrl,this.promo.promoName=e.promoName,this.promo.promoPackageName=e.promoPackageName,this.promo.promoValue=e.promoValue.toString(),console.log(this.promo),this.editListState="edit",this.innerVisible=!0},addToEditTable:function(){var e=this;this.$refs.promo.validate(function(t){if(!t)return!1;for(var o=!0,a=0;a<e.EditTable.length;a++)if(e.EditTable[a].promoPackageName===e.promo.promoPackageName){o=!1;break}if(console.log("添加到推广列表配置",e.promo.promoEdit.uid),console.log("添加到推广列表图片",e.promo.fodder.qiniu_file_path),o){var n={appid:e.uid,uid:e.promo.promoEdit.uid,puid:e.promo.fodder.qiniu_file_guid,channelPackage:"暂无"===e.promo.channelPackage?"":e.promo.channelPackage,channelRule:e.promo.channelRule,promoIconUrl:e.promo.promoIconUrl,promoName:e.promo.promoName,promoPackageName:e.promo.promoPackageName,promoValue:e.promo.promoValue.toString()},r={appid:e.uid,uid:e.promo.promoEdit.uid,puid:e.promo.fodder.qiniu_file_guid,promoValue:e.promo.promoValue.toString()};Object(i.b)(r).then(function(t){var o=!0;3e3===t.repcode&&(n.id=t.data.id,e.EditTable.push(n),e.innerVisible=!1,o=!1),o&&e.$message({message:"添加推广列表关联失败！",type:"warning"})}).catch(function(t){console.error(t),e.$message({message:"添加推广列表关联失败！",type:"warning"})})}else e.$message({message:"请勿重复添加！",type:"warning"})})},editToEditTable:function(){var e=this;this.$refs.promo.validate(function(t){if(!t)return!1;var o={appid:e.uid,uid:e.promo.promoEdit.uid,puid:e.promo.fodder.qiniu_file_guid,id:e.promo.id,promoValue:e.promo.promoValue.toString()};Object(i.f)(o).then(function(t){var o=!0;3e3===t.repcode&&(e.$set(e.EditTable[e.promoListIndex],"uid",e.promo.promoEdit.uid),e.$set(e.EditTable[e.promoListIndex],"puid",e.promo.fodder.qiniu_file_guid),e.$set(e.EditTable[e.promoListIndex],"promoValue",e.promo.promoValue),e.$set(e.EditTable[e.promoListIndex],"channelPackage","暂无"===e.promo.channelPackage?"":e.promo.channelPackage),e.$set(e.EditTable[e.promoListIndex],"channelRule",e.promo.channelRule),e.$set(e.EditTable[e.promoListIndex],"promoIconUrl",e.promo.promoIconUrl),e.$set(e.EditTable[e.promoListIndex],"promoName",e.promo.promoName),e.$set(e.EditTable[e.promoListIndex],"promoPackageName",e.promo.promoPackageName),e.innerVisible=!1,o=!1),o&&e.$message({message:"添加推广列表关联失败！",type:"warning"})}).catch(function(t){console.error(t),e.$message({message:"添加推广列表关联失败！",type:"warning"})})})},addPromo:function(){var e=this;this.promo={},this.$nextTick(function(){e.$refs.promo.clearValidate()}),this.$set(this.promo,"promoValue",1),this.editListState="add",this.innerVisible=!0},initPromoEdit:function(){var e=this;Object(i.g)().then(function(t){3e3==t.repcode?(console.log(t.data),e.channelPromoEditList=t.data,console.log("推广配置表",e.channelPromoEditList)):(console.error(res.data),e.$message({message:"获取互推配置表失败！",type:"warning"}))}).catch(function(t){console.error(t),e.$message({message:"获取互推配置表失败！",type:"warning"})})},addPromoEdit:function(){var e=this;this.$refs.channelPromoEdit.validate(function(t){if(!t)return!1;var o={};if(e.switchValue){if(void 0===e.channelPromoEdit.channelRule_custom||0===e.channelPromoEdit.channelRule_custom.length)return void e.$message({message:"自定义跳转规则不能为空！",type:"warning"});o={channel:e.channelPromoEdit.channel,channelPackage:e.channelPromoEdit.channelPackage,promoPackageName:e.channelPromoEdit.promoPackageName,promoName:e.channelPromoEdit.promoName,channelRule:e.channelPromoEdit.channelRule_custom,uid:e.channelPromoEdit.uid}}else o={channel:e.channelPromoEdit.channel,channelPackage:e.channelPromoEdit.channelPackage,promoPackageName:e.channelPromoEdit.promoPackageName,promoName:e.channelPromoEdit.promoName,channelRule:e.channelPromoEdit.channelRule_shop,uid:e.channelPromoEdit.uid};console.log(o),Object(i.a)(o).then(function(t){3e3==t.repcode?(e.$message({message:"添加互推配置成功！",type:"success"}),e.promoEditVisible=!1,e.initPromoEdit()):(console.error(res.data),e.$message({message:"添加互推配置失败！",type:"warning"}))}).catch(function(t){console.error(t),e.$message({message:"添加互推配置失败！",type:"warning"})})})},editPromoEdit:function(){var e=this;this.$refs.channelPromoEdit.validate(function(t){if(!t)return!1;var o={};if(e.switchValue){if(void 0===e.channelPromoEdit.channelRule_custom||0===e.channelPromoEdit.channelRule_custom.length)return void e.$message({message:"自定义跳转规则不能为空！",type:"warning"});o={channel:e.channelPromoEdit.channel,channelPackage:e.channelPromoEdit.channelPackage,promoPackageName:e.channelPromoEdit.promoPackageName,promoName:e.channelPromoEdit.promoName,channelRule:e.channelPromoEdit.channelRule_custom,uid:e.channelPromoEdit.uid}}else o={channel:e.channelPromoEdit.channel,channelPackage:e.channelPromoEdit.channelPackage,promoPackageName:e.channelPromoEdit.promoPackageName,promoName:e.channelPromoEdit.promoName,channelRule:e.channelPromoEdit.channelRule_shop,uid:e.channelPromoEdit.uid};console.log(o),Object(i.e)(o).then(function(t){3e3==t.repcode?(e.$message({message:"修改互推配置成功！",type:"success"}),e.promoEditVisible=!1,e.initPromoEdit()):(console.error(res.data),e.$message({message:"修改互推配置失败！",type:"warning"}))}).catch(function(t){console.error(t),e.$message({message:"修改互推配置失败！",type:"warning"})})})},deleteRow:function(e,t){var o=this;this.$confirm("确认删除？").then(function(a){var n={id:t[e].id};Object(i.d)(n).then(function(a){(a.repcode=3e3)?t.splice(e,1):o.$message({message:"移除互推列表失败！",type:"warning"})}).catch(function(e){console.error(e),o.$message({message:"移除互推列表失败！",type:"warning"})})}).catch(function(e){return!1})},fodderChange:function(e){console.log("选择器素材change",e),this.$set(this.promo,"promoIconUrl",e.qiniu_file_path)},promoEditChange:function(e){console.log("选择器推广配置change",e),this.$set(this.promo,"channelPackage",e.channelPackage),this.$set(this.promo,"channelRule",e.channelRule),this.$set(this.promo,"promoName",e.promoName),this.$set(this.promo,"promoPackageName",e.promoPackageName),console.log(this.promo)},showPromoEditDialog:function(e){var t=this;this.appchannel=e.channel,this.channelPromoEdit={},this.$nextTick(function(){t.$refs.channelPromoEdit.clearValidate()}),console.log("推广配置按钮",e);for(var o=!0,a=0,i=0;i<this.channelPromoEditList.length;i++)if(this.channelPromoEditList[i].uid===e.uid){o=!1,a=i;break}if(this.switchDisabled=!1,o){this.channelPromoEdit={},this.editState="add",this.switchValue=!0,this.channelPromoEdit.channelRule="market://details?id={0}",this.channelPromoEdit.uid=e.uid,this.channelPromoEdit.channel=e.channel,this.channelPromoEdit.promoName=e.appName,this.channelPromoEdit.promoPackageName=e.packageName;for(var n=!0,r=0;r<this.promoChannelList.length;r++)if(this.promoChannelList[r].promoChannelMark===e.channel){this.channelPromoEdit.channelPackage=this.promoChannelList[r].promoChannelPackage,this.$set(this.channelPromoEdit,"channelRule_shop",this.promoChannelList[r].channelRule),n=!1;break}n&&(this.switchDisabled=!0,this.$set(this.channelPromoEdit,"channelRule_shop","market://details?id={0}"),this.channelPromoEdit.channelPackage="暂无")}else{if(this.editState="edit",this.switchValue=!0,this.channelPromoEdit.uid=this.channelPromoEditList[a].uid,this.channelPromoEdit.channel=this.channelPromoEditList[a].channel,this.channelPromoEdit.promoName=this.channelPromoEditList[a].promoName,this.channelPromoEdit.promoPackageName=this.channelPromoEditList[a].promoPackageName,this.channelPromoEdit.channelRule=this.channelPromoEditList[a].channelRule,"暂无"===this.channelPromoEditList[a].channelPackage){for(var l=!0,s=0;s<this.promoChannelList.length;s++)if(this.promoChannelList[s].promoChannelMark===e.channel){this.channelPromoEdit.channelPackage=this.promoChannelList[s].promoChannelPackage,l=!1;break}l&&(this.switchDisabled=!0,this.channelPromoEdit.channelPackage="暂无")}else this.channelPromoEdit.channelPackage=this.channelPromoEditList[a].channelPackage;for(var c=!0,m=0;m<this.promoChannelList.length;m++)if(this.promoChannelList[m].promoChannelMark===e.channel){this.$set(this.channelPromoEdit,"channelRule_shop",this.promoChannelList[m].channelRule),c=!1;break}c&&this.$set(this.channelPromoEdit,"channelRule_shop","market://details?id={0}"),this.switchValue=!0,this.$set(this.channelPromoEdit,"channelRule_custom",this.channelPromoEditList[a].channelRule)}this.promoEditVisible=!0},getRowPromoListMeth:function(e,t){var o=this;Object(i.i)(e).then(function(e){3e3==e.repcode?(o.EditTable=e.data,t&&o.editPromoList(!0)):(console.error(e.data),o.$message({message:"获取应用参数id失败！",type:"warning"}))}).catch(function(e){console.error(e),o.$message({message:"获取应用参数id失败！",type:"warning"})})},showPromoListDialog:function(e){var t=this,o=this;this.uid=e.uid,this.EditTable=[];var n={appId:e.uid};console.log(this.promoChannelListRel),Object(a.f)(n).then(function(e){if(3e3==e.repcode){var a=JSON.parse(e.data);console.log(a);for(var r=!0,l=!1,s="",c=0;c<a.rows.length;c++)if("channelPromo"===a.rows[c].system){JSON.parse(a.rows[c].status);if(console.log("推广列表点击事件(外网)",a.rows[c]),console.log("rowid",a.rows[c].id),t.id=a.rows[c].id,"[]"===a.rows[c].status){r=!1,t.promoListState="edit";break}void 0===JSON.parse(a.rows[c].status)[0].id&&(console.log("需要同步"),l=!0,s=a.rows[c].status),r=!1,t.promoListState="edit";break}if(r&&(t.promoListState="add"),l){var m=JSON.parse(s);console.log("需要同步的数据",m);var d={list:m,appid:t.uid};o.$notify({title:"提示",type:"warning",message:"正在同步渠道数据-----"}),Object(i.k)(d).then(function(e){3e3===e.repcode?(o.$notify({title:"提示",type:"success",message:"同步渠道数据成功-----"}),t.getRowPromoListMeth(n,!0),t.promoListVisible=!0):t.$message({message:"同步渠道列表失败！",type:"warning"})}).catch(function(e){console.error("同步渠道列表失败",e),t.$message({message:"同步渠道列表失败！",type:"warning"})})}else t.getRowPromoListMeth(n),t.promoListVisible=!0}else console.error(e.data),t.$message({message:"获取应用参数id失败！",type:"warning"})}).catch(function(e){console.error(e),t.$message({message:"获取应用参数id失败！",type:"warning"})})},showCurrentPromoList:function(e){var t=this;this.EditTable=[];var o={appId:e.uid};Object(a.f)(o).then(function(e){if(3e3==e.repcode){var o=JSON.parse(e.data);console.log(o);for(var a=0;a<o.rows.length;a++)if("channelPromo"===o.rows[a].system){var i=JSON.parse(o.rows[a].status);console.log("当前配置",o.rows[a]),console.log("rowid",o.rows[a].id),t.id=o.rows[a].id,t.EditTable=i;break}t.currentPromoListVisible=!0}else console.error(e.data),t.$message({message:"获取应用参数失败！",type:"warning"})}).catch(function(e){console.error(e),t.$message({message:"获取应用参数失败！",type:"warning"})})},initFodder:function(){var e=this;Object(i.j)().then(function(t){3e3==t.repcode?e.fodderList=t.data:e.$message.error("表格初始化失败！")}).catch(function(e){console.error(e)})},initSelect:function(){var e=this;Object(a.b)().then(function(e){var t=JSON.parse(e.data);console.log(t)}).catch(function(t){e.$message({message:"初始化表格失败！",type:"warning"})})},searchAppList:function(e){var t=this,o={appName:this.fetchForm.appName,appId:this.fetchForm.uid,appPackageName:this.fetchForm.packageName,page:e,limit:this.pageSize};Object(a.h)(o).then(function(e){var o=JSON.parse(e.data);t.tableData=o.rows,t.search_totalPages=o.total}).catch(function(e){t.$message({message:"搜索失败！",type:"warning"})})},search_pageChange:function(e){this.def_currentpage=e,this.searchAppList(e)},searchData:function(){this.def_currentpage=1,this.searchAppList(1),this.def_paging=!1,this.search_paging=!0,this.searchDialog=!1},pageChange:function(e){this.def_currentpage=e,this.listapplist(e)},initTable:function(){this.def_currentpage=1,this.listapplist(1)},listapplist:function(e){var t=this,o={page:e,limit:this.pageSize};Object(a.d)(o).then(function(e){var o=JSON.parse(e.data);console.log("表格初始化",o.rows),t.tableData=o.rows,t.totalPages=o.total}).catch(function(e){t.$message({message:"初始化表格失败！",type:"warning"})})},flushList:function(){this.def_paging?this.pageChange(this.def_currentpage):this.search_pageChange(this.def_currentpage)},checkPermission:o("41Be").a}},l=(o("DK83"),o("KHd+")),s=Object(l.a)(r,function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{staticClass:"components-container"},[o("el-button",{staticStyle:{margin:"20px"},attrs:{type:"primary"},on:{click:function(t){e.searchDialog=!0}}},[e._v("搜索应用")]),e._v(" "),o("el-dialog",{attrs:{title:"搜索应用",visible:e.searchDialog,width:"30%"},on:{"update:visible":function(t){e.searchDialog=t}}},[o("el-form",{ref:"fetchForm",attrs:{model:e.fetchForm,"label-width":"200px"}},[o("el-form-item",{attrs:{label:"应用名 : "}},[o("el-input",{staticStyle:{width:"200px"},attrs:{clearable:""},model:{value:e.fetchForm.appName,callback:function(t){e.$set(e.fetchForm,"appName",t)},expression:"fetchForm.appName"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"屏蔽key : "}},[o("el-input",{staticStyle:{width:"200px"},attrs:{clearable:""},model:{value:e.fetchForm.uid,callback:function(t){e.$set(e.fetchForm,"uid",t)},expression:"fetchForm.uid"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"包名 : "}},[o("el-input",{staticStyle:{width:"200px"},attrs:{clearable:""},model:{value:e.fetchForm.packageName,callback:function(t){e.$set(e.fetchForm,"packageName",t)},expression:"fetchForm.packageName"}})],1)],1),e._v(" "),o("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[o("el-button",{attrs:{type:"primary"},on:{click:e.searchData}},[e._v("搜索")])],1)],1),e._v(" "),o("el-card",{attrs:{shadow:"always"}},[o("el-table",{staticStyle:{width:"100%"},attrs:{height:"730",border:"",data:e.tableData}},[o("el-table-column",{attrs:{label:"序号",type:"index",width:"50"}}),e._v(" "),o("el-table-column",{attrs:{prop:"appName",label:"应用名",width:"180"}}),e._v(" "),o("el-table-column",{attrs:{prop:"uid",label:"屏蔽key",width:"300"}}),e._v(" "),o("el-table-column",{attrs:{prop:"packageName",label:"应用包名"}}),e._v(" "),o("el-table-column",{attrs:{prop:"channel",label:"渠道标识"}}),e._v(" "),o("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[o("el-button",{attrs:{size:"mini"},on:{click:function(o){e.showPromoEditDialog(t.row)}}},[e._v("被推规则")]),e._v(" "),o("el-button",{attrs:{size:"mini"},on:{click:function(o){e.showPromoListDialog(t.row)}}},[e._v("推广列表")]),e._v(" "),o("el-button",{attrs:{size:"mini"},on:{click:function(o){e.showCurrentPromoList(t.row)}}},[e._v("当前配置")])]}}])})],1),e._v(" "),e.def_paging?o("el-pagination",{attrs:{"page-size":e.pageSize,layout:"prev, pager, next",total:e.totalPages,"current-page":e.def_currentpage},on:{"current-change":e.pageChange}}):e._e(),e._v(" "),e.search_paging?o("el-pagination",{attrs:{"page-size":e.pageSize,layout:"prev, pager, next",total:e.search_totalPages,"current-page":e.search_currentpage},on:{"current-change":e.search_pageChange}}):e._e()],1),e._v(" "),o("el-dialog",{attrs:{title:"被推规则",visible:e.promoEditVisible,width:"40%"},on:{"update:visible":function(t){e.promoEditVisible=t}}},[o("el-form",{ref:"channelPromoEdit",staticStyle:{"margin-left":"50px"},attrs:{model:e.channelPromoEdit,"label-position":"left","label-width":"100px","status-icon":""}},[o("el-form-item",{attrs:{label:"推广应用名",rules:[{required:!0,message:"推广应用名不能为空"}],prop:"promoName","label-width":"120px"}},[o("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~",disabled:""},model:{value:e.channelPromoEdit.promoName,callback:function(t){e.$set(e.channelPromoEdit,"promoName",t)},expression:"channelPromoEdit.promoName"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"渠道包名",rules:[{required:!0,message:"渠道包名不能为空"}],prop:"channelPackage","label-width":"120px"}},[o("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~",disabled:""},model:{value:e.channelPromoEdit.channelPackage,callback:function(t){e.$set(e.channelPromoEdit,"channelPackage",t)},expression:"channelPromoEdit.channelPackage"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"跳转规则","label-width":"120px"}},[o("el-switch",{attrs:{disabled:e.switchDisabled,"active-text":"自定义","inactive-text":"商店"},model:{value:e.switchValue,callback:function(t){e.switchValue=t},expression:"switchValue"}}),e._v(" "),o("br"),e._v(" "),e.switchValue?o("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~channelRule_custom"},model:{value:e.channelPromoEdit.channelRule_custom,callback:function(t){e.$set(e.channelPromoEdit,"channelRule_custom",t)},expression:"channelPromoEdit.channelRule_custom"}}):e._e(),e._v(" "),e.switchValue?e._e():o("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~ channelRule_shop",disabled:""},model:{value:e.channelPromoEdit.channelRule_shop,callback:function(t){e.$set(e.channelPromoEdit,"channelRule_shop",t)},expression:"channelPromoEdit.channelRule_shop"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"推广包名",rules:[{required:!0,message:"推广包名不能为空"}],prop:"promoPackageName","label-width":"120px"}},[o("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~",disabled:""},model:{value:e.channelPromoEdit.promoPackageName,callback:function(t){e.$set(e.channelPromoEdit,"promoPackageName",t)},expression:"channelPromoEdit.promoPackageName"}})],1)],1),e._v(" "),o("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[o("el-button",{on:{click:function(t){e.promoEditVisible=!1}}},[e._v(e._s("取消"))]),e._v(" "),"add"===e.editState?o("el-button",{attrs:{type:"primary"},on:{click:e.addPromoEdit}},[e._v(e._s("添加"))]):e._e(),e._v(" "),"edit"===e.editState?o("el-button",{attrs:{type:"primary"},on:{click:e.editPromoEdit}},[e._v(e._s("修改"))]):e._e()],1)],1),e._v(" "),o("el-dialog",{attrs:{title:"推广列表",visible:e.promoListVisible,width:"90%"},on:{"update:visible":function(t){e.promoListVisible=t}}},[o("el-button",{staticStyle:{"margin-bottom":"15px"},attrs:{type:"primary"},on:{click:e.addPromo}},[e._v("新增互推")]),e._v(" "),o("el-dialog",{attrs:{width:"600px",title:"互推",visible:e.innerVisible,"append-to-body":""},on:{"update:visible":function(t){e.innerVisible=t}}},[o("el-form",{ref:"promo",staticClass:"demo-ruleForm",attrs:{model:e.promo,"label-width":"100px"}},[o("el-form-item",{attrs:{label:"被推规则","label-width":"120px",prop:"promoEdit",rules:[{required:!0,message:"被推规则不能为空"}]}},[o("el-select",{staticStyle:{width:"300px"},attrs:{placeholder:"必选",filterable:"","value-key":"uid"},on:{change:e.promoEditChange},model:{value:e.promo.promoEdit,callback:function(t){e.$set(e.promo,"promoEdit",t)},expression:"promo.promoEdit"}},e._l(e.channelPromoEditList,function(t){return o("el-option",{key:t.uid,attrs:{label:t.uid,value:t}},[o("span",{staticStyle:{float:"left"}},[e._v(e._s(t.promoName))]),e._v(" "),o("span",{staticStyle:{float:"right",color:"#8492a6","font-size":"13px"}},[e._v(e._s(t.channel))])])}))],1),e._v(" "),o("el-form-item",{attrs:{label:"推广应用名",rules:[{required:!0,message:"推广应用名不能为空"}],prop:"promoName","label-width":"120px"}},[o("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~",disabled:""},model:{value:e.promo.promoName,callback:function(t){e.$set(e.promo,"promoName",t)},expression:"promo.promoName"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"渠道包名",rules:[{required:!0,message:"渠道包名不能为空"}],prop:"channelPackage","label-width":"120px"}},[o("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~",disabled:""},model:{value:e.promo.channelPackage,callback:function(t){e.$set(e.promo,"channelPackage",t)},expression:"promo.channelPackage"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"跳转规则",rules:[{required:!0,message:"跳转规则不能为空"}],prop:"channelRule","label-width":"120px"}},[o("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~",disabled:""},model:{value:e.promo.channelRule,callback:function(t){e.$set(e.promo,"channelRule",t)},expression:"promo.channelRule"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"推广包名",rules:[{required:!0,message:"推广包名不能为空"}],prop:"promoPackageName","label-width":"120px"}},[o("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~",disabled:""},model:{value:e.promo.promoPackageName,callback:function(t){e.$set(e.promo,"promoPackageName",t)},expression:"promo.promoPackageName"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"素材","label-width":"120px",rules:[{required:!0,message:"素材不能为空"}],prop:"fodder"}},[o("el-select",{staticStyle:{width:"400px"},attrs:{placeholder:"必选",filterable:"","value-key":"qiniu_file_guid"},on:{change:e.fodderChange},model:{value:e.promo.fodder,callback:function(t){e.$set(e.promo,"fodder",t)},expression:"promo.fodder"}},e._l(e.fodderList,function(t){return o("el-option",{key:t.qiniu_file_guid,attrs:{label:t.qiniu_file_name,value:t}},[o("span",{staticStyle:{float:"left"}},[e._v(e._s(t.qiniu_file_name))]),e._v(" "),o("span",{staticStyle:{float:"right",color:"#8492a6","font-size":"13px"}},[o("img",{attrs:{src:t.qiniu_file_path,width:"50",height:"50"}})])])}))],1),e._v(" "),o("el-form-item",{attrs:{label:"推广图片链接",rules:[{required:!0,message:"推广图片链接不能为空"}],prop:"promoIconUrl","label-width":"120px"}},[o("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~",disabled:""},model:{value:e.promo.promoIconUrl,callback:function(t){e.$set(e.promo,"promoIconUrl",t)},expression:"promo.promoIconUrl"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"推广权值",rules:[{required:!0,message:"推广权值不能为空"}],prop:"promoValue","label-width":"120px"}},[o("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~",type:"number"},model:{value:e.promo.promoValue,callback:function(t){e.$set(e.promo,"promoValue",e._n(t))},expression:"promo.promoValue"}})],1)],1),e._v(" "),o("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[o("el-button",{on:{click:function(t){e.innerVisible=!1}}},[e._v(e._s("取消"))]),e._v(" "),"add"===e.editListState?o("el-button",{attrs:{type:"primary"},on:{click:e.addToEditTable}},[e._v(e._s("确认添加"))]):e._e(),e._v(" "),"edit"===e.editListState?o("el-button",{attrs:{type:"primary"},on:{click:e.editToEditTable}},[e._v(e._s("立即修改"))]):e._e()],1)],1),e._v(" "),o("el-table",{staticStyle:{width:"100%"},attrs:{height:"530",border:"",data:e.EditTable}},[o("el-table-column",{attrs:{label:"序号",type:"index",width:"50"}}),e._v(" "),o("el-table-column",{attrs:{prop:"promoName",label:"推广应用名",width:"180"}}),e._v(" "),o("el-table-column",{attrs:{prop:"channelPackage",label:"渠道包名",width:"280"}}),e._v(" "),o("el-table-column",{attrs:{prop:"channelRule",label:"跳转规则",width:"450"}}),e._v(" "),o("el-table-column",{attrs:{prop:"promoPackageName",label:"推广包名"}}),e._v(" "),o("el-table-column",{attrs:{width:"350",prop:"promoIconUrl",label:"推广图片链接"}}),e._v(" "),o("el-table-column",{attrs:{width:"80",prop:"promoValue",label:"推广权值"}}),e._v(" "),e.checkPermission(["admin"])?o("el-table-column",{attrs:{width:"80",prop:"appid",label:"appid"}}):e._e(),e._v(" "),e.checkPermission(["admin"])?o("el-table-column",{attrs:{width:"80",prop:"uid",label:"uid"}}):e._e(),e._v(" "),e.checkPermission(["admin"])?o("el-table-column",{attrs:{width:"80",prop:"puid",label:"puid"}}):e._e(),e._v(" "),e.checkPermission(["admin"])?o("el-table-column",{attrs:{width:"80",prop:"id",label:"id"}}):e._e(),e._v(" "),o("el-table-column",{attrs:{fixed:"right",label:"操作",width:"120"},scopedSlots:e._u([{key:"default",fn:function(t){return[o("el-button",{attrs:{type:"text",size:"small"},nativeOn:{click:function(o){o.preventDefault(),e.deleteRow(t.$index,e.EditTable)}}},[e._v("\n            移除\n          ")]),e._v(" "),o("el-button",{attrs:{type:"text",size:"small"},nativeOn:{click:function(o){o.preventDefault(),e.EditRow(t.row,t.$index)}}},[e._v("\n            编辑\n          ")])]}}])})],1),e._v(" "),o("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[o("el-button",{on:{click:function(t){e.promoListVisible=!1}}},[e._v(e._s("取消"))]),e._v(" "),"add"===e.promoListState?o("el-button",{attrs:{type:"primary"},on:{click:e.addPromoList}},[e._v(e._s("添加"))]):e._e(),e._v(" "),"edit"===e.promoListState?o("el-button",{attrs:{type:"primary"},on:{click:function(t){e.editPromoList(!1)}}},[e._v(e._s("发布"))]):e._e()],1)],1),e._v(" "),o("el-dialog",{attrs:{title:"应用当前配置",visible:e.currentPromoListVisible,width:"90%"},on:{"update:visible":function(t){e.currentPromoListVisible=t}}},[o("el-table",{staticStyle:{width:"100%"},attrs:{height:"530",border:"",data:e.EditTable}},[o("el-table-column",{attrs:{label:"序号",type:"index",width:"50"}}),e._v(" "),o("el-table-column",{attrs:{prop:"promoName",label:"推广应用名",width:"180"}}),e._v(" "),o("el-table-column",{attrs:{prop:"channelPackage",label:"渠道包名",width:"280"}}),e._v(" "),o("el-table-column",{attrs:{prop:"channelRule",label:"跳转规则",width:"450"}}),e._v(" "),o("el-table-column",{attrs:{prop:"promoPackageName",label:"推广包名"}}),e._v(" "),o("el-table-column",{attrs:{width:"350",prop:"promoIconUrl",label:"推广图片链接"}}),e._v(" "),o("el-table-column",{attrs:{width:"80",prop:"promoValue",label:"推广权值"}}),e._v(" "),e.checkPermission(["admin"])?o("el-table-column",{attrs:{width:"80",prop:"appid",label:"appid"}}):e._e(),e._v(" "),e.checkPermission(["admin"])?o("el-table-column",{attrs:{width:"80",prop:"uid",label:"uid"}}):e._e(),e._v(" "),e.checkPermission(["admin"])?o("el-table-column",{attrs:{width:"80",prop:"puid",label:"puid"}}):e._e(),e._v(" "),o("el-table-column",{attrs:{width:"80",prop:"id",label:"id"}})],1),e._v(" "),o("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"})],1)],1)},[],!1,null,"73a4c87a",null);s.options.__file="channelPromoEdit.vue";t.default=s.exports},YXBG:function(e,t,o){"use strict";o.d(t,"e",function(){return i}),o.d(t,"g",function(){return n}),o.d(t,"d",function(){return r}),o.d(t,"b",function(){return l}),o.d(t,"h",function(){return s}),o.d(t,"f",function(){return c}),o.d(t,"a",function(){return m}),o.d(t,"c",function(){return d});var a=o("t3Un");function i(e){return Object(a.a)({url:"/api/system/getip",method:"post",data:e})}function n(e){return Object(a.a)({url:"/api/system/postip",method:"post",data:e})}function r(e){return Object(a.a)({url:"/api/system/getapplist",method:"post",data:e})}function l(){return Object(a.a)({url:"/api/system/applist",method:"post"})}function s(e){return Object(a.a)({url:"/api/system/searchapp",method:"post",data:e})}function c(e){return Object(a.a)({url:"/api/system/getparams",method:"post",data:e})}function m(e){return Object(a.a)({url:"/api/system/addparams",method:"post",data:e})}function d(e){return Object(a.a)({url:"/api/system/editparams",method:"post",data:e})}},lwSp:function(e,t,o){"use strict";o.d(t,"g",function(){return i}),o.d(t,"a",function(){return n}),o.d(t,"e",function(){return r}),o.d(t,"h",function(){return l}),o.d(t,"b",function(){return s}),o.d(t,"f",function(){return c}),o.d(t,"d",function(){return m}),o.d(t,"c",function(){return d}),o.d(t,"i",function(){return p}),o.d(t,"j",function(){return h}),o.d(t,"k",function(){return u});var a=o("t3Un");function i(){return Object(a.a)({url:"/api/system/getChannelPromoEdit",method:"get"})}function n(e){return Object(a.a)({url:"/api/system/addChannelPromoEdit",method:"post",data:e})}function r(e){return Object(a.a)({url:"/api/system/EditChannelPromoEdit",method:"patch",data:e})}function l(){return Object(a.a)({url:"/api/system/getPromoList",method:"get"})}function s(e){return Object(a.a)({url:"/api/system/addPromoList",method:"post",data:e})}function c(e){return Object(a.a)({url:"/api/system/editPromoList",method:"patch",data:e})}function m(e){return Object(a.a)({url:"/api/system/delPromoList",method:"delete",params:e})}function d(e){return Object(a.a)({url:"/api/system/addPromoListRecord",method:"post",data:e})}function p(e){return Object(a.a)({url:"/api/system/getRowPromoList",method:"get",params:e})}function h(){return Object(a.a)({url:"/qiniufile",method:"get"})}function u(e){return Object(a.a)({url:"/api/system/synchroPromoList",method:"post",data:e})}}}]);
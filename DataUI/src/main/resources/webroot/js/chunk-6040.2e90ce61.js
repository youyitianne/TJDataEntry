(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-6040"],{Mz3J:function(t,e,a){"use strict";Math.easeInOutQuad=function(t,e,a,i){return(t/=i/2)<1?a/2*t*t+e:-a/2*(--t*(t-2)-1)+e};var i=window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(t){window.setTimeout(t,1e3/60)};function n(t,e,a){var n=document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop,l=t-n,o=0;e=void 0===e?500:e;!function t(){o+=20,function(t){document.documentElement.scrollTop=t,document.body.parentNode.scrollTop=t,document.body.scrollTop=t}(Math.easeInOutQuad(o,n,l,e)),o<e?i(t):a&&"function"==typeof a&&a()}()}var l={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(t){this.$emit("update:page",t)}},pageSize:{get:function(){return this.limit},set:function(t){this.$emit("update:limit",t)}}},methods:{handleSizeChange:function(t){this.$emit("pagination",{page:this.currentPage,limit:t}),this.autoScroll&&n(0,800)},handleCurrentChange:function(t){this.$emit("pagination",{page:t,limit:this.pageSize}),this.autoScroll&&n(0,800)}}},o=(a("PelQ"),a("KHd+")),s=Object(o.a)(l,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"pagination-container",class:{hidden:t.hidden}},[a("el-pagination",t._b({attrs:{background:t.background,"current-page":t.currentPage,"page-size":t.pageSize,layout:t.layout,total:t.total},on:{"update:currentPage":function(e){t.currentPage=e},"update:pageSize":function(e){t.pageSize=e},"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}},"el-pagination",t.$attrs,!1))],1)},[],!1,null,"7d9f0a7c",null);s.options.__file="index.vue";e.a=s.exports},PelQ:function(t,e,a){"use strict";var i=a("X9ZH");a.n(i).a},"RU/L":function(t,e,a){a("Rqdy");var i=a("WEpk").Object;t.exports=function(t,e,a){return i.defineProperty(t,e,a)}},Rqdy:function(t,e,a){var i=a("Y7ZC");i(i.S+i.F*!a("jmDH"),"Object",{defineProperty:a("2faE").f})},SEkw:function(t,e,a){t.exports={default:a("RU/L"),__esModule:!0}},X9ZH:function(t,e,a){},Xdbf:function(t,e,a){"use strict";a.r(e);var i=a("P2sY"),n=a.n(i),l=a("YEIV"),o=a.n(l),s=a("t3Un");var r=a("ZySA"),u=(a("7Qib"),{components:{Pagination:a("Mz3J").a},directives:{waves:r.a},filters:{statusFilter:function(t){return{published:"success",draft:"gray",deleted:"danger"}[t]}},data:function(){return o()({create_flag:!0,update_flag:!0,inputName:"",hidlist:[],introduce:"介绍",pickerOptions0:{disabledDate:function(t){return t.getTime()>Date.now()-864e4}},addButton:"添加应用",directives:{waves:r.a},downloadLoading:!1,layout:"",timevalue:"",searchName:"搜索",tableKey:0,list:null,total:0,listLoading:!1,importanceOptions:[1,2,3],names:[],sortOptions:[{label:"ID Ascending",key:"+id"},{label:"ID Descending",key:"-id"}],statusOptions:["published","draft","deleted"],dialogStatus:"",dialogFormVisible:!1,app:{id:void 0,name:void 0,system:void 0,icon:void 0,introduce:void 0},textMap:{update:"编辑",create:"创建"},rules:{title:[{required:!0,message:"必须有名字！",trigger:"blur"}]},dialogPvVisible:!1,pvData:[]},"sortOptions",[{label:"ID Ascending",key:"+id"},{label:"ID Descending",key:"-id"}])},mounted:function(){this.handleFilter()},methods:o()({createData:function(){for(var t=this,e=0;e<this.hidlist.length;e++)if(this.hidlist[e].name===this.app.name)return void this.$message({message:"该应用已存在~",type:"warning"});if(""!==this.app.introduce&&""!==this.app.name&&""!==this.app.icon&&""!==this.app.system){var a=this;this.create_flag&&(this.create_flag=!1,function(t){return Object(s.a)({url:"/app",method:"post",params:t})}(this.app).then(function(){t.handleFilter(),t.list.unshift(t.app),t.dialogFormVisible=!1,t.$notify({title:"成功",message:"创建成功",type:"success",duration:2e3}),t.create_flag=!0}).catch(function(t){a.dialogFormVisible=!1,a.$notify({title:"失败",message:"请稍后重试",type:"error",duration:2e3}),this.create_flag=!0}))}else this.open3()},updateData:function(){var t=this,e=this,a=n()({},this.app);""!==a.introduce&&""!==a.name&&""!==a.icon&&""!==a.system?this.update_flag&&(this.update_flag=!1,function(t){return Object(s.a)({url:"/app/"+t.id,method:"patch",data:{name:t.name,system:t.system,icon:t.icon,introduce:t.introduce}})}(a).then(function(){t.dialogFormVisible=!1,t.handleFilter(),t.$notify({title:"成功",message:"更新成功",type:"success",duration:2e3}),t.update_flag=!0}).catch(function(t){e.dialogFormVisible=!1,e.$notify({title:"失败",message:"请稍后重试",type:"error",duration:2e3}),this.update_flag=!0})):this.open3()},handleCreate:function(){var t=this;this.resetTemp(),this.dialogStatus="create",this.dialogFormVisible=!0,this.$nextTick(function(){t.$refs.dataForm.clearValidate()})},handleUpdate:function(t){var e=this;this.app=n()({},t),this.dialogStatus="update",this.dialogFormVisible=!0,this.$nextTick(function(){e.$refs.dataForm.clearValidate()})},handleDelete:function(t){var e=this,a=this;this.$confirm("是否确定删除?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){(function(t){return Object(s.a)({url:"/app/"+t.id,method:"delete"})})(t).then(function(){e.handleFilter(),e.$notify({title:"成功",message:"删除成功",type:"success",duration:2e3})}).catch(function(t){a.$notify({title:"失败",message:"请稍后重试",type:"error",duration:2e3})})})},resetTemp:function(){this.app={id:void 0,name:"",system:"安卓",icon:"无",introduce:"无"}},open3:function(){this.$message({message:"记得选择查询范围~",type:"warning"})},open4:function(){this.$message({message:"没有信息可以打印~",type:"warning"})},handleFilter:function(){var t=this;Object(s.a)({url:"/app",method:"get"}).then(function(e){t.hidlist=e.data,t.list=e.data,t.listLoading=!1}).catch(function(t){console.log(t)})},getDatawithName:function(){this.listLoading=!0;var t=this.inputName;if(""==t)return this.list=this.hidlist,void(this.listLoading=!1);for(var e=[],a=0;a<this.hidlist.length;a++)-1!=this.hidlist[a].name.search(t)&&e.push(this.hidlist[a]);this.list=e,this.listLoading=!1},formatJson:function(t,e){return e.map(function(e){return t.map(function(t){return e[t]})})}},"open3",function(){this.$message({message:"内容不能为空~",type:"warning"})})}),c=a("KHd+"),d=Object(c.a)(u,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container",staticStyle:{margin:"15px","margin-top":"-5px"}},[a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"primary",icon:"el-icon-edit"},on:{click:t.handleCreate}},[t._v(t._s(t.addButton)+"\n    ")]),t._v(" "),a("el-input",{staticClass:"filter-item",staticStyle:{width:"200px"},attrs:{placeholder:"根据账号查找",clearable:""},on:{blur:t.getDatawithName},model:{value:t.inputName,callback:function(e){t.inputName=e},expression:"inputName"}})],1),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],attrs:{stripe:"",data:t.list,"element-loading-text":"Loading",border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{align:"center",label:"ID",width:"95"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s(++e.$index)+"\n      ")]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"应用名",width:"110",align:"center",prop:"date"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.name))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"平台",width:"110",align:"center",prop:"app_name"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.system)+"\n      ")]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"图标",prop:"channel"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.icon))])]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"介绍",prop:"advertising_type"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.introduce))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"操作",align:"center",width:"230","class-name":"small-padding fixed-width"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(a){t.handleUpdate(e.row)}}},[t._v(t._s("编辑"))]),t._v(" "),a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){t.handleDelete(e.row)}}},[t._v(t._s("删除"))])]}}])})],1),t._v(" "),a("el-dialog",{attrs:{title:t.textMap[t.dialogStatus],visible:t.dialogFormVisible},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[a("el-form",{ref:"dataForm",staticStyle:{width:"400px","margin-left":"50px"},attrs:{model:t.app,"label-position":"left","label-width":"90px"}},["update"===this.dialogStatus?a("el-form-item",{attrs:{label:"应用名"}},[a("el-input",{attrs:{placeholder:"请输入游戏名字~",disabled:""},model:{value:t.app.name,callback:function(e){t.$set(t.app,"name",e)},expression:"app.name"}})],1):t._e(),t._v(" "),"create"===this.dialogStatus?a("el-form-item",{attrs:{label:"应用名"}},[a("el-input",{attrs:{placeholder:"请输入游戏名字~"},model:{value:t.app.name,callback:function(e){t.$set(t.app,"name",e)},expression:"app.name"}})],1):t._e(),t._v(" "),a("el-form-item",{attrs:{label:"平台"}},[a("el-input",{attrs:{placeholder:"默认填写安卓~"},model:{value:t.app.system,callback:function(e){t.$set(t.app,"system",e)},expression:"app.system"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"图标"}},[a("el-input",{attrs:{placeholder:"暂无此功能~",value:"无"},model:{value:t.app.icon,callback:function(e){t.$set(t.app,"icon",e)},expression:"app.icon"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"介绍"}},[a("el-input",{attrs:{placeholder:"比如别称~"},model:{value:t.app.introduce,callback:function(e){t.$set(t.app,"introduce",e)},expression:"app.introduce"}})],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogFormVisible=!1}}},[t._v(t._s("取消"))]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(e){"create"===t.dialogStatus?t.createData():t.updateData()}}},[t._v(t._s("确认"))])],1)],1),t._v(" "),a("el-dialog",{attrs:{visible:t.dialogPvVisible,title:"Reading statistics"},on:{"update:visible":function(e){t.dialogPvVisible=e}}},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.pvData,border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{prop:"key",label:"Channel"}}),t._v(" "),a("el-table-column",{attrs:{prop:"pv",label:"Pv"}})],1),t._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(e){t.dialogPvVisible=!1}}},[t._v(t._s("219"))])],1)],1)],1)},[],!1,null,null,null);d.options.__file="appTable.vue";e.default=d.exports},YEIV:function(t,e,a){"use strict";e.__esModule=!0;var i=function(t){return t&&t.__esModule?t:{default:t}}(a("SEkw"));e.default=function(t,e,a){return e in t?(0,i.default)(t,e,{value:a,enumerable:!0,configurable:!0,writable:!0}):t[e]=a,t}},ZySA:function(t,e,a){"use strict";var i=a("P2sY"),n=a.n(i),l=(a("jUE0"),{bind:function(t,e){t.addEventListener("click",function(a){var i=n()({},e.value),l=n()({ele:t,type:"hit",color:"rgba(0, 0, 0, 0.15)"},i),o=l.ele;if(o){o.style.position="relative",o.style.overflow="hidden";var s=o.getBoundingClientRect(),r=o.querySelector(".waves-ripple");switch(r?r.className="waves-ripple":((r=document.createElement("span")).className="waves-ripple",r.style.height=r.style.width=Math.max(s.width,s.height)+"px",o.appendChild(r)),l.type){case"center":r.style.top=s.height/2-r.offsetHeight/2+"px",r.style.left=s.width/2-r.offsetWidth/2+"px";break;default:r.style.top=(a.pageY-s.top-r.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",r.style.left=(a.pageX-s.left-r.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return r.style.backgroundColor=l.color,r.className="waves-ripple z-active",!1}},!1)}}),o=function(t){t.directive("waves",l)};window.Vue&&(window.waves=l,Vue.use(o)),l.install=o;e.a=l},jUE0:function(t,e,a){}}]);
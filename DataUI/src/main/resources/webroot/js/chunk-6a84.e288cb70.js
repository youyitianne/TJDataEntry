(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-6a84"],{Mz3J:function(t,e,n){"use strict";Math.easeInOutQuad=function(t,e,n,i){return(t/=i/2)<1?n/2*t*t+e:-n/2*(--t*(t-2)-1)+e};var i=window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(t){window.setTimeout(t,1e3/60)};function a(t,e,n){var a=document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop,l=t-a,o=0;e=void 0===e?500:e;!function t(){o+=20,function(t){document.documentElement.scrollTop=t,document.body.parentNode.scrollTop=t,document.body.scrollTop=t}(Math.easeInOutQuad(o,a,l,e)),o<e?i(t):n&&"function"==typeof n&&n()}()}var l={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(t){this.$emit("update:page",t)}},pageSize:{get:function(){return this.limit},set:function(t){this.$emit("update:limit",t)}}},methods:{handleSizeChange:function(t){this.$emit("pagination",{page:this.currentPage,limit:t}),this.autoScroll&&a(0,800)},handleCurrentChange:function(t){this.$emit("pagination",{page:t,limit:this.pageSize}),this.autoScroll&&a(0,800)}}},o=(n("PelQ"),n("KHd+")),s=Object(o.a)(l,function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"pagination-container",class:{hidden:t.hidden}},[n("el-pagination",t._b({attrs:{background:t.background,"current-page":t.currentPage,"page-size":t.pageSize,layout:t.layout,total:t.total},on:{"update:currentPage":function(e){t.currentPage=e},"update:pageSize":function(e){t.pageSize=e},"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}},"el-pagination",t.$attrs,!1))],1)},[],!1,null,"7d9f0a7c",null);s.options.__file="index.vue";e.a=s.exports},PelQ:function(t,e,n){"use strict";var i=n("X9ZH");n.n(i).a},"RU/L":function(t,e,n){n("Rqdy");var i=n("WEpk").Object;t.exports=function(t,e,n){return i.defineProperty(t,e,n)}},Rqdy:function(t,e,n){var i=n("Y7ZC");i(i.S+i.F*!n("jmDH"),"Object",{defineProperty:n("2faE").f})},SEkw:function(t,e,n){t.exports={default:n("RU/L"),__esModule:!0}},X9ZH:function(t,e,n){},YEIV:function(t,e,n){"use strict";e.__esModule=!0;var i=function(t){return t&&t.__esModule?t:{default:t}}(n("SEkw"));e.default=function(t,e,n){return e in t?(0,i.default)(t,e,{value:n,enumerable:!0,configurable:!0,writable:!0}):t[e]=n,t}},ZySA:function(t,e,n){"use strict";var i=n("P2sY"),a=n.n(i),l=(n("jUE0"),{bind:function(t,e){t.addEventListener("click",function(n){var i=a()({},e.value),l=a()({ele:t,type:"hit",color:"rgba(0, 0, 0, 0.15)"},i),o=l.ele;if(o){o.style.position="relative",o.style.overflow="hidden";var s=o.getBoundingClientRect(),r=o.querySelector(".waves-ripple");switch(r?r.className="waves-ripple":((r=document.createElement("span")).className="waves-ripple",r.style.height=r.style.width=Math.max(s.width,s.height)+"px",o.appendChild(r)),l.type){case"center":r.style.top=s.height/2-r.offsetHeight/2+"px",r.style.left=s.width/2-r.offsetWidth/2+"px";break;default:r.style.top=(n.pageY-s.top-r.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",r.style.left=(n.pageX-s.left-r.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return r.style.backgroundColor=l.color,r.className="waves-ripple z-active",!1}},!1)}}),o=function(t){t.directive("waves",l)};window.Vue&&(window.waves=l,Vue.use(o)),l.install=o;e.a=l},jUE0:function(t,e,n){},"ts+I":function(t,e,n){"use strict";n.r(e);var i=n("P2sY"),a=n.n(i),l=n("YEIV"),o=n.n(l),s=n("t3Un");var r=n("ZySA"),c=(n("7Qib"),{components:{Pagination:n("Mz3J").a},directives:{waves:r.a},filters:{statusFilter:function(t){return{published:"success",draft:"gray",deleted:"danger"}[t]}},data:function(){return o()({inputName:"",hidlist:[],introduce:"介绍",pickerOptions0:{disabledDate:function(t){return t.getTime()>Date.now()-864e4}},addButton:"添加渠道",directives:{waves:r.a},downloadLoading:!1,layout:"",timevalue:"",tableKey:0,list:null,total:0,listLoading:!1,importanceOptions:[1,2,3],names:[],sortOptions:[{label:"ID Ascending",key:"+id"},{label:"ID Descending",key:"-id"}],statusOptions:["published","draft","deleted"],dialogStatus:"",dialogFormVisible:!1,channel:{id:void 0,name:void 0,program_mark:void 0,note:void 0},textMap:{update:"编辑",create:"创建"},rules:{title:[{required:!0,message:"必须有名字！",trigger:"blur"}]},dialogPvVisible:!1,pvData:[]},"sortOptions",[{label:"ID Ascending",key:"+id"},{label:"ID Descending",key:"-id"}])},mounted:function(){this.handleFilter()},methods:{createData:function(){for(var t=this,e=this,n=0;n<this.hidlist.length;n++)if(this.hidlist[n].name===this.channel.name)return void this.$message({message:"该渠道已存在~",type:"warning"});""!==this.channel.name&&""!==this.channel.program_mark&&""!==this.channel.note?function(t){return Object(s.a)({url:"/channel",method:"post",params:t})}(this.channel).then(function(){t.handleFilter(),t.list.unshift(t.channel),t.dialogFormVisible=!1,t.$notify({title:"成功",message:"创建成功",type:"success",duration:2e3})}).catch(function(t){e.dialogFormVisible=!1,console.log(t)}):this.open3()},updateData:function(){for(var t=this,e=this,n=a()({},this.channel),i=0;i<this.hidlist.length;i++)if(this.hidlist[i].name===n.name)return void this.$message({message:"该渠道已存在~",type:"warning"});""!==n.program_mark&&""!==n.name&&""!==n.note?function(t){return Object(s.a)({url:"/channel/"+t.id,method:"patch",data:{name:t.name,program_mark:t.program_mark,note:t.note}})}(n).then(function(){t.dialogFormVisible=!1,t.handleFilter(),t.$notify({title:"成功",message:"更新成功",type:"success",duration:2e3})}).catch(function(t){e.dialogFormVisible=!1,e.$notify({title:"失败",message:"请稍后重试",type:"success",duration:2e3})}):this.open3()},handleCreate:function(){var t=this;this.resetTemp(),this.dialogStatus="create",this.dialogFormVisible=!0,this.$nextTick(function(){t.$refs.dataForm.clearValidate()})},handleUpdate:function(t){var e=this;this.channel=a()({},t),this.dialogStatus="update",this.dialogFormVisible=!0,this.$nextTick(function(){e.$refs.dataForm.clearValidate()})},handleDelete:function(t){var e=this,n=this;this.$confirm("是否确定删除?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){(function(t){return Object(s.a)({url:"/channel/"+t.id,method:"delete"})})(t).then(function(){e.handleFilter(),e.$notify({title:"成功",message:"删除成功",type:"success",duration:2e3})}).catch(function(t){n.$notify({title:"失败",message:"请稍后重试",type:"success",duration:2e3})})})},resetTemp:function(){this.channel={id:void 0,name:"",program_mark:"",note:"无"}},handleFilter:function(){var t=this,e=this;Object(s.a)({url:"/channel",method:"get"}).then(function(e){t.list=e.data,t.hidlist=e.data,console.log(t.hidlist),t.listLoading=!1}).catch(function(t){console.log(t),e.listLoading=!1})},getDatawithName:function(){this.listLoading=!0;var t=this.inputName;if(""==t)return this.list=this.hidlist,void(this.listLoading=!1);for(var e=[],n=0;n<this.hidlist.length;n++)-1!=this.hidlist[n].name.search(t)&&e.push(this.hidlist[n]);this.list=e,this.listLoading=!1},formatJson:function(t,e){return e.map(function(e){return t.map(function(t){return e[t]})})},open3:function(){this.$message({message:"内容不能为空~",type:"warning"})}}}),u=n("KHd+"),d=Object(u.a)(c,function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"app-container"},[n("div",{staticClass:"filter-container",staticStyle:{margin:"15px","margin-top":"-5px"}},[n("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"primary",icon:"el-icon-edit"},on:{click:t.handleCreate}},[t._v(t._s(t.addButton)+"\n    ")]),t._v(" "),n("el-input",{staticClass:"filter-item",staticStyle:{width:"200px","margin-bottom":"15px"},attrs:{placeholder:"根据渠道名称查找",clearable:""},on:{blur:t.getDatawithName},model:{value:t.inputName,callback:function(e){t.inputName=e},expression:"inputName"}})],1),t._v(" "),n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],attrs:{data:t.list,"element-loading-text":"Loading",border:"",fit:"",stripe:"","highlight-current-row":""}},[n("el-table-column",{attrs:{align:"center",label:"ID",width:"95"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s(++e.$index)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{label:"渠道名称",width:"110",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("span",[t._v(t._s(e.row.name))])]}}])}),t._v(" "),n("el-table-column",{attrs:{label:"渠道标记",width:"110",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n        "+t._s(e.row.program_mark)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{align:"center",label:"备注"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("span",[t._v(t._s(e.row.note))])]}}])}),t._v(" "),n("el-table-column",{attrs:{label:"操作",align:"center",width:"230","class-name":"small-padding fixed-width"},scopedSlots:t._u([{key:"default",fn:function(e){return[n("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(n){t.handleUpdate(e.row)}}},[t._v(t._s("编辑"))]),t._v(" "),n("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(n){t.handleDelete(e.row)}}},[t._v(t._s("删除"))])]}}])})],1),t._v(" "),n("el-dialog",{attrs:{title:t.textMap[t.dialogStatus],visible:t.dialogFormVisible},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[n("el-form",{ref:"dataForm",staticStyle:{width:"400px","margin-left":"50px"},attrs:{model:t.channel,"label-position":"left","label-width":"90px"}},[n("el-form-item",{attrs:{label:"渠道名称"}},[n("el-input",{attrs:{placeholder:"请输入渠道名字~"},model:{value:t.channel.name,callback:function(e){t.$set(t.channel,"name",e)},expression:"channel.name"}})],1),t._v(" "),n("el-form-item",{attrs:{label:"渠道标记"}},[n("el-input",{attrs:{placeholder:"渠道的英文名称~"},model:{value:t.channel.program_mark,callback:function(e){t.$set(t.channel,"program_mark",e)},expression:"channel.program_mark"}})],1),t._v(" "),n("el-form-item",{attrs:{label:"备注"}},[n("el-input",{attrs:{placeholder:"介绍一个这个渠道吧~",value:"无"},model:{value:t.channel.note,callback:function(e){t.$set(t.channel,"note",e)},expression:"channel.note"}})],1)],1),t._v(" "),n("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{on:{click:function(e){t.dialogFormVisible=!1}}},[t._v(t._s("取消"))]),t._v(" "),n("el-button",{attrs:{type:"primary"},on:{click:function(e){"create"===t.dialogStatus?t.createData():t.updateData()}}},[t._v(t._s("确认"))])],1)],1),t._v(" "),n("el-dialog",{attrs:{visible:t.dialogPvVisible,title:"Reading statistics"},on:{"update:visible":function(e){t.dialogPvVisible=e}}},[n("el-table",{staticStyle:{width:"100%"},attrs:{data:t.pvData,border:"",fit:"","highlight-current-row":""}},[n("el-table-column",{attrs:{prop:"key",label:"Channel"}}),t._v(" "),n("el-table-column",{attrs:{prop:"pv",label:"Pv"}})],1),t._v(" "),n("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{attrs:{type:"primary"},on:{click:function(e){t.dialogPvVisible=!1}}},[t._v(t._s("219"))])],1)],1)],1)},[],!1,null,null,null);d.options.__file="channelTable.vue";e.default=d.exports}}]);
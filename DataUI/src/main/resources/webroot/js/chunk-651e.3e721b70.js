(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-651e"],{"/aNP":function(e,t,a){"use strict";a.d(t,"c",function(){return n}),a.d(t,"a",function(){return l}),a.d(t,"d",function(){return o}),a.d(t,"b",function(){return r});var i=a("t3Un");function n(){return Object(i.a)({url:"/api/account",method:"get"})}function l(e){return Object(i.a)({url:"/api/account",method:"post",params:e})}function o(e){return Object(i.a)({url:"/api/account/"+e.id,method:"patch",data:{username:e.username,password:e.psd,department:e.department,position:e.position,note:e.note}})}function r(e){return Object(i.a)({url:"/api/account/"+e.id,method:"delete"})}},Mz3J:function(e,t,a){"use strict";Math.easeInOutQuad=function(e,t,a,i){return(e/=i/2)<1?a/2*e*e+t:-a/2*(--e*(e-2)-1)+t};var i=window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(e){window.setTimeout(e,1e3/60)};function n(e,t,a){var n=document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop,l=e-n,o=0;t=void 0===t?500:t;!function e(){o+=20,function(e){document.documentElement.scrollTop=e,document.body.parentNode.scrollTop=e,document.body.scrollTop=e}(Math.easeInOutQuad(o,n,l,t)),o<t?i(e):a&&"function"==typeof a&&a()}()}var l={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(e){this.$emit("update:page",e)}},pageSize:{get:function(){return this.limit},set:function(e){this.$emit("update:limit",e)}}},methods:{handleSizeChange:function(e){this.$emit("pagination",{page:this.currentPage,limit:e}),this.autoScroll&&n(0,800)},handleCurrentChange:function(e){this.$emit("pagination",{page:e,limit:this.pageSize}),this.autoScroll&&n(0,800)}}},o=(a("PelQ"),a("KHd+")),r=Object(o.a)(l,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"pagination-container",class:{hidden:e.hidden}},[a("el-pagination",e._b({attrs:{background:e.background,"current-page":e.currentPage,"page-size":e.pageSize,layout:e.layout,total:e.total},on:{"update:currentPage":function(t){e.currentPage=t},"update:pageSize":function(t){e.pageSize=t},"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}},"el-pagination",e.$attrs,!1))],1)},[],!1,null,"7d9f0a7c",null);r.options.__file="index.vue";t.a=r.exports},PelQ:function(e,t,a){"use strict";var i=a("X9ZH");a.n(i).a},"RU/L":function(e,t,a){a("Rqdy");var i=a("WEpk").Object;e.exports=function(e,t,a){return i.defineProperty(e,t,a)}},Rqdy:function(e,t,a){var i=a("Y7ZC");i(i.S+i.F*!a("jmDH"),"Object",{defineProperty:a("2faE").f})},SEkw:function(e,t,a){e.exports={default:a("RU/L"),__esModule:!0}},X9ZH:function(e,t,a){},YEIV:function(e,t,a){"use strict";t.__esModule=!0;var i=function(e){return e&&e.__esModule?e:{default:e}}(a("SEkw"));t.default=function(e,t,a){return t in e?(0,i.default)(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}},ZySA:function(e,t,a){"use strict";var i=a("P2sY"),n=a.n(i),l=(a("jUE0"),{bind:function(e,t){e.addEventListener("click",function(a){var i=n()({},t.value),l=n()({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},i),o=l.ele;if(o){o.style.position="relative",o.style.overflow="hidden";var r=o.getBoundingClientRect(),s=o.querySelector(".waves-ripple");switch(s?s.className="waves-ripple":((s=document.createElement("span")).className="waves-ripple",s.style.height=s.style.width=Math.max(r.width,r.height)+"px",o.appendChild(s)),l.type){case"center":s.style.top=r.height/2-s.offsetHeight/2+"px",s.style.left=r.width/2-s.offsetWidth/2+"px";break;default:s.style.top=(a.pageY-r.top-s.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",s.style.left=(a.pageX-r.left-s.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return s.style.backgroundColor=l.color,s.className="waves-ripple z-active",!1}},!1)}}),o=function(e){e.directive("waves",l)};window.Vue&&(window.waves=l,Vue.use(o)),l.install=o;t.a=l},jUE0:function(e,t,a){},rUrQ:function(e,t,a){"use strict";a.r(t);var i=a("P2sY"),n=a.n(i),l=a("YEIV"),o=a.n(l),r=a("/aNP"),s=a("t3Un");var c=a("ZySA"),u=(a("7Qib"),[{key:"管理员",val:"admin"},{key:"运营",val:"operator"},{key:"商务",val:"market"},{key:"策划",val:"planner"},{key:"技术",val:"developer"},{key:"组长",val:"leader"}]),d={components:{Pagination:a("Mz3J").a},directives:{waves:c.a},filters:{statusFilter:function(e){return{published:"success",draft:"gray",deleted:"danger"}[e]}},data:function(){return o()({inputName:"",detectionid:12,hackReset:!1,checkedlist:[],hidlist:[],data2:[{id:"fileupload-operate",label:"上传文件"},{label:"数据管理",children:[{id:"addata-operate",label:"广告数据管理"},{id:"yixindata-operate",label:"移信数据下载"}]},{label:"项目管理",children:[{label:"应用管理",children:[{id:"app-canList",label:"应用展示"},{id:"app-canCreate",label:"应用添加"},{id:"app-canEdit",label:"应用修改"},{id:"app-canDelete",label:"应用删除"}]},{label:"渠道管理",children:[{id:"channel-canList",label:"渠道展示"},{id:"channel-canCreate",label:"渠道添加"},{id:"channel-canEdit",label:"渠道修改"},{id:"channel-canDelete",label:"渠道删除"}]},{label:"广告类型管理",children:[{id:"adtype-canList",label:"广告类型展示"},{id:"adtype-canCreate",label:"广告类型添加"},{id:"adtype-canEdit",label:"广告类型修改"},{id:"adtype-canDelete",label:"广告类型删除"}]}]},{label:"系统管理",children:[{label:"用户管理",children:[{id:"user-canList",label:"用户展示"},{id:"user-canCreate",label:"用户添加"},{id:"user-canEdit",label:"用户修改"},{id:"user-canDelete",label:"用户删除"}]},{label:"角色管理",children:[{id:"role-canList",label:"角色展示"},{id:"role-canCreate",label:"角色添加"},{id:"role-canEdit",label:"角色修改"},{id:"role-canDelete",label:"角色删除"}]},{label:"权限管理",children:[{id:"permission-canList",label:"权限展示"},{id:"permission-canCreate",label:"权限添加"},{id:"permission-canEdit",label:"权限修改"},{id:"permission-canDelete",label:"权限删除"}]},{label:"资源管理管理",children:[{id:"resource-canList",label:"资源展示"},{id:"resource-canCreate",label:"资源添加"},{id:"resource-canEdit",label:"资源修改"},{id:"resource-canDelete",label:"资源删除"}]}]}],defaultProps:{children:"children",label:"label"},grade:{box:!1,check:[]},permFormVisible:!1,roleOptions:u,introduce:"介绍",pickerOptions0:{disabledDate:function(e){return e.getTime()>Date.now()-864e4}},addButton:"添加角色",directives:{waves:c.a},downloadLoading:!1,layout:"",timevalue:"",searchName:"搜索",tableKey:0,list:null,userlist:null,total:0,listLoading:!0,importanceOptions:[1,2,3],names:[],sortOptions:[{label:"ID Ascending",key:"+id"},{label:"ID Descending",key:"-id"}],dialogStatus:"",dialogFormVisible:!1,app:{id:void 0,name:void 0,system:void 0,icon:void 0,introduce:void 0},textMap:{update:"编辑",create:"创建",perm:"权限编辑"},rules:{title:[{required:!0,message:"必须有名字！",trigger:"blur"}]},dialogPvVisible:!1,permlist:null,pvData:[]},"sortOptions",[{label:"ID Ascending",key:"+id"},{label:"ID Descending",key:"-id"}])},mounted:function(){this.handleFilter(),this.getUsernames()},methods:o()({permFormClose:function(){this.permFormVisible=!1},handleUpdatePerm:function(){var e=this,t=this;(function(e){return Object(s.a)({url:"/api/perms",method:"post",params:e})})({username:this.permlist.username,id:this.permlist.id,username_mark:this.permlist.username_mark,permissions:this.$refs.tree.getCheckedKeys().toString()}).then(function(){e.handleFilter(),e.permFormVisible=!1,e.$notify({title:"成功",message:"更新成功",type:"success",duration:2e3})}).catch(function(e){t.permFormClose(),console.log(e)})},handlePerm:function(e){var t=this;this.hackReset=!1;this.permlist=e,this.dialogStatus="update",function(e){return Object(s.a)({url:"/api/perms/"+e.username_mark,method:"get"})}(e).then(function(e){void 0!=t.$refs.tree&&t.$refs.tree.store.setCheckedKeys([]),t.grade.check=e.data,t.hackReset=!0,t.permFormVisible=!0}).catch(function(e){console.log(e)})},getUsernames:function(){var e=this;Object(r.c)().then(function(t){e.userlist=t.data,e.listLoading=!1}).catch(function(e){console.log(e)})},createData:function(){var e=this;if(""!==this.app.username&&""!==this.app.role&&""!==this.app.note&&""!==this.app.role_name&&""!==this.app.role_describe){for(var t=this.app.username,a=null,i=0;i<this.userlist.length;i++)t==this.userlist[i].username&&(a=this.userlist[i].id);var n=this;(function(e,t){return Object(s.a)({url:"/api/role",method:"post",data:{username:e.username,username_mark:t,role:e.role,note:e.note,role_name:e.role_name,role_describe:e.role_describe}})})(this.app,a).then(function(){e.handleFilter(),e.list.unshift(e.app),e.dialogFormVisible=!1,e.$notify({title:"成功",message:"创建成功",type:"success",duration:2e3})}).catch(function(e){n.dialogFormVisible=!1})}else this.open3()},updateData:function(){var e=this,t=n()({},this.app);if(""!==t.username&&""!==t.role&&""!==t.note&&""!==t.role_name&&""!==t.role_describe){var a=this;(function(e){return Object(s.a)({url:"/api/role/"+e.id,method:"patch",data:{role:e.role,note:e.note,role_name:e.role_name,role_describe:e.role_describe}})})(t).then(function(){e.handleFilter(),e.$notify({title:"成功",message:"更新成功",type:"success",duration:2e3}),e.dialogFormVisible=!1}).catch(function(e){a.dialogFormVisible=!1,console.log(e)})}else this.open3()},handleCreate:function(){var e=this;this.resetTemp(),this.dialogStatus="create",this.dialogFormVisible=!0,this.$nextTick(function(){e.$refs.dataForm.clearValidate()})},handleUpdate:function(e){var t=this;this.app=n()({},e),this.dialogStatus="update",this.dialogFormVisible=!0,this.$nextTick(function(){t.$refs.dataForm.clearValidate()})},handleDelete:function(e){var t=this;this.$confirm("是否确定删除?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){(function(e){return Object(s.a)({url:"/api/role/"+e.id,method:"delete"})})(e).then(function(){t.handleFilter(),t.$notify({title:"成功",message:"删除成功",type:"success",duration:2e3})}).catch(function(e){console.log(e)})})},resetTemp:function(){this.app={username:"",role:"",note:"",role_name:"",role_describe:""}},open3:function(){this.$message({message:"记得选择查询范围~",type:"warning"})},open4:function(){this.$message({message:"没有信息可以打印~",type:"warning"})},handleFilter:function(){var e=this,t=this;Object(s.a)({url:"/api/role",method:"get"}).then(function(t){e.list=t.data,e.hidlist=t.data,e.listLoading=!1}).catch(function(e){console.log(e),t.listLoading=!1})},getRolewithName:function(){this.listLoading=!0;var e=this.inputName;if(""==e)return this.list=this.hidlist,void(this.listLoading=!1);for(var t=[],a=0;a<this.hidlist.length;a++)-1!=this.hidlist[a].username.search(e)&&t.push(this.hidlist[a]);this.list=t,this.listLoading=!1},formatJson:function(e,t){return t.map(function(t){return e.map(function(e){return t[e]})})}},"open3",function(){this.$message({message:"内容不能为空~",type:"warning"})})},p=a("KHd+"),m=Object(p.a)(d,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container",staticStyle:{margin:"15px","margin-top":"-5px"}},[a("el-button",{staticClass:"filter-item",staticStyle:{"margin-left":"10px"},attrs:{type:"primary",icon:"el-icon-edit"},on:{click:e.handleCreate}},[e._v(e._s(e.addButton)+"\n    ")]),e._v(" "),a("el-input",{staticClass:"filter-item",staticStyle:{width:"200px"},attrs:{placeholder:"根据账号查找",clearable:""},on:{blur:e.getRolewithName},model:{value:e.inputName,callback:function(t){e.inputName=t},expression:"inputName"}})],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],attrs:{data:e.list,"element-loading-text":"Loading",border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{align:"center",label:"ID",width:"95"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(++t.$index)+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"帐号",width:"300",align:"center",prop:"date"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.username))])]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"角色名称",width:"300",align:"center",prop:"app_name"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(t.row.role_name)+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"角色标识",width:"300",align:"center",prop:"app_name"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(t.row.role)+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"角色描述",width:"300",align:"center",prop:"app_name"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(t.row.role_describe)+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"备注",align:"center",prop:"channel"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.note))])]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"操作",align:"center",width:"230","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(a){e.handleUpdate(t.row)}}},[e._v("编辑")]),e._v(" "),a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){e.handleDelete(t.row)}}},[e._v("删除")]),e._v(" "),a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(a){e.handlePerm(t.row)}}},[e._v("权限")])]}}])})],1),e._v(" "),a("el-dialog",{attrs:{title:e.textMap[e.dialogStatus],visible:e.dialogFormVisible},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"dataForm",staticStyle:{width:"400px","margin-left":"50px"},attrs:{model:e.app,"label-position":"left","label-width":"90px"}},[a("el-form-item",{attrs:{label:"帐号"}},["create"===e.dialogStatus?a("el-select",{staticClass:"filter-item",attrs:{placeholder:"帐号"},model:{value:e.app.username,callback:function(t){e.$set(e.app,"username",t)},expression:"app.username"}},e._l(e.userlist,function(e){return a("el-option",{key:e.username,attrs:{label:e.username,value:e.username}})})):e._e(),e._v(" "),"update"===e.dialogStatus?a("el-select",{staticClass:"filter-item",attrs:{placeholder:"帐号",disabled:""},model:{value:e.app.username,callback:function(t){e.$set(e.app,"username",t)},expression:"app.username"}},e._l(e.userlist,function(e){return a("el-option",{key:e.username,attrs:{label:e.username,value:e.username}})})):e._e()],1),e._v(" "),a("el-form-item",{attrs:{label:"角色名字"}},[a("el-input",{attrs:{placeholder:"必填",value:"无"},model:{value:e.app.role_name,callback:function(t){e.$set(e.app,"role_name",t)},expression:"app.role_name"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"角色标识"}},[a("el-select",{staticClass:"filter-item",attrs:{placeholder:"请选择权限"},model:{value:e.app.role,callback:function(t){e.$set(e.app,"role",t)},expression:"app.role"}},e._l(e.roleOptions,function(e){return a("el-option",{key:e.key,attrs:{label:e.key,value:e.val}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"角色描述"}},[a("el-input",{attrs:{placeholder:"必填",value:"无"},model:{value:e.app.role_describe,callback:function(t){e.$set(e.app,"role_describe",t)},expression:"app.role_describe"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"备注"}},[a("el-input",{attrs:{placeholder:"必填",value:"无"},model:{value:e.app.note,callback:function(t){e.$set(e.app,"note",t)},expression:"app.note"}})],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v(e._s("取消"))]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){"create"===e.dialogStatus?e.createData():e.updateData()}}},[e._v(e._s("确认"))])],1)],1),e._v(" "),a("el-dialog",{attrs:{visible:e.dialogPvVisible,title:"Reading statistics"},on:{"update:visible":function(t){e.dialogPvVisible=t}}},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.pvData,border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{prop:"key",label:"Channel"}}),e._v(" "),a("el-table-column",{attrs:{prop:"pv",label:"Pv"}})],1),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.dialogPvVisible=!1}}},[e._v(e._s("219"))])],1)],1),e._v(" "),e.hackReset?a("el-dialog",{attrs:{title:e.textMap.perm,visible:e.permFormVisible,detectionid:e.detectionid},on:{"update:visible":function(t){e.permFormVisible=t}}},[a("el-tree",{ref:"tree",attrs:{data:e.data2,"node-key":"id","default-checked-keys":e.grade.check,"default-expanded-keys":e.grade.check,"show-checkbox":"","highlight-current":"",props:e.defaultProps}}),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.permFormClose()}}},[e._v(e._s("取消"))]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.handleUpdatePerm()}}},[e._v(e._s("确认"))])],1)],1):e._e()],1)},[],!1,null,null,null);m.options.__file="user_role_Table.vue";t.default=m.exports}}]);
(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-ccea"],{"41Be":function(e,t,n){"use strict";n.d(t,"a",function(){return i});var a=n("Q2AE");function i(e){if(e&&e instanceof Array&&e.length>0){var t=e;return!!(a.a.getters&&a.a.getters.roles).some(function(e){return t.includes(e)})}return!1}},Mz3J:function(e,t,n){"use strict";Math.easeInOutQuad=function(e,t,n,a){return(e/=a/2)<1?n/2*e*e+t:-n/2*(--e*(e-2)-1)+t};var a=window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(e){window.setTimeout(e,1e3/60)};function i(e,t,n){var i=document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop,o=e-i,r=0;t=void 0===t?500:t;!function e(){r+=20,function(e){document.documentElement.scrollTop=e,document.body.parentNode.scrollTop=e,document.body.scrollTop=e}(Math.easeInOutQuad(r,i,o,t)),r<t?a(e):n&&"function"==typeof n&&n()}()}var o={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(e){this.$emit("update:page",e)}},pageSize:{get:function(){return this.limit},set:function(e){this.$emit("update:limit",e)}}},methods:{handleSizeChange:function(e){this.$emit("pagination",{page:this.currentPage,limit:e}),this.autoScroll&&i(0,800)},handleCurrentChange:function(e){this.$emit("pagination",{page:e,limit:this.pageSize}),this.autoScroll&&i(0,800)}}},r=(n("PelQ"),n("KHd+")),l=Object(r.a)(o,function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"pagination-container",class:{hidden:e.hidden}},[n("el-pagination",e._b({attrs:{background:e.background,"current-page":e.currentPage,"page-size":e.pageSize,layout:e.layout,total:e.total},on:{"update:currentPage":function(t){e.currentPage=t},"update:pageSize":function(t){e.pageSize=t},"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}},"el-pagination",e.$attrs,!1))],1)},[],!1,null,"7d9f0a7c",null);l.options.__file="index.vue";t.a=l.exports},PelQ:function(e,t,n){"use strict";var a=n("X9ZH");n.n(a).a},Smkn:function(e,t,n){"use strict";n.r(t);var a=n("P2sY"),i=n.n(a),o=n("t3Un");var r=n("ZySA"),l=(n("7Qib"),n("Mz3J")),s=n("41Be"),u=n("Q2AE"),c={components:{Pagination:l.a},directives:{waves:r.a},filters:{statusFilter:function(e){return{published:"success",draft:"gray",deleted:"danger"}[e]}},data:function(){return{inputName:"",hidlist:[],resourcelist:null,data2:[],checkedlist:[],defaultProps:{children:"children",label:"label"},resourceFormVisible:!1,resourceName:"资源编辑",list:null,listLoading:!1}},mounted:function(){this.handleFilter(),this.initTree()},methods:{initTree:function(){var e=this;Object(o.a)({url:"/getapplist",method:"get"}).then(function(t){e.data2=t.data}).catch(function(e){console.log(e)})},updateResource:function(){for(var e=this,t=this.$refs.tree.getCheckedNodes(),n="",a=0;a<t.length;a++)n+=t[a].label,a!=t.length-1&&(n+=",");(function(e){return Object(o.a)({url:"/api/resource",method:"post",params:e})})({username:this.resourcelist.username,username_mark:this.resourcelist.username_mark,resources:this.$refs.tree.getCheckedKeys().toString(),resources_name:n}).then(function(){e.handleFilter(),e.resourceFormVisible=!1,e.$notify({title:"成功",message:"更新成功",type:"success",duration:2e3})}).catch(function(e){console.log(e)})},handleResource:function(e){var t=this;this.resourcelist=e,this.app=i()({},e),function(e){return Object(o.a)({url:"/api/resource/"+e.username_mark,method:"get"})}(e).then(function(e){void 0!=t.$refs.tree&&t.$refs.tree.setCheckedKeys([]),t.checkedlist=e.data,t.resourceFormVisible=!0}).catch(function(e){console.log(e)})},handleFilter:function(){var e=this,t=this;Object(o.a)({url:"/api/role",method:"get"}).then(function(n){var a=u.a.getters&&u.a.getters.name,i=n.data,o=[];if(Object(s.a)(["admin"]))for(var r=0;r<i.length;r++)i[r].username!=a&&"leader"!=i[r].role&&o.push(i[r]);else if(Object(s.a)(["operator","planner","developer","market"]))for(var l=e.check(),c=0;c<i.length;c++)i[c].role==l&&i[c].username!=a&&o.push(i[c]);e.list=o,e.hidlist=o,t.listLoading=!1}).catch(function(e){console.log(e),t.listLoading=!1})},getDatawithName:function(){this.listLoading=!0;var e=this.inputName;if(""==e)return this.list=this.hidlist,void(this.listLoading=!1);for(var t=[],n=0;n<this.hidlist.length;n++)-1!=this.hidlist[n].username.search(e)&&t.push(this.hidlist[n]);this.list=t,this.listLoading=!1},formatJson:function(e,t){return t.map(function(t){return e.map(function(e){return t[e]})})},check:function(){for(var e="未知",t=["operator","planner","developer","market"],n=0;n<t.length;n++)Object(s.a)([t[n]])&&(e=t[n]);return e}}},d=n("KHd+"),p=Object(d.a)(c,function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"app-container"},[n("el-input",{staticClass:"filter-item",staticStyle:{width:"200px","margin-bottom":"15px"},attrs:{placeholder:"根据账号查找",clearable:""},on:{blur:e.getDatawithName},model:{value:e.inputName,callback:function(t){e.inputName=t},expression:"inputName"}}),e._v(" "),n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],attrs:{data:e.list,"element-loading-text":"Loading",border:"",fit:"","highlight-current-row":""}},[n("el-table-column",{attrs:{align:"center",label:"ID",width:"95"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(++t.$index)+"\n      ")]}}])}),e._v(" "),n("el-table-column",{attrs:{label:"帐号",width:"300",align:"center",prop:"date"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("span",[e._v(e._s(t.row.username))])]}}])}),e._v(" "),n("el-table-column",{attrs:{label:"角色名称",width:"300",align:"center",prop:"app_name"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(t.row.role_name)+"\n      ")]}}])}),e._v(" "),n("el-table-column",{attrs:{label:"角色标识",width:"300",align:"center",prop:"app_name"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(t.row.role)+"\n      ")]}}])}),e._v(" "),n("el-table-column",{attrs:{label:"角色描述",width:"300",align:"center",prop:"app_name"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(t.row.role_describe)+"\n      ")]}}])}),e._v(" "),n("el-table-column",{attrs:{label:"备注",align:"center",prop:"channel"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("span",[e._v(e._s(t.row.note))])]}}])}),e._v(" "),n("el-table-column",{attrs:{label:"操作",align:"center",width:"230","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(n){e.handleResource(t.row)}}},[e._v(e._s("资源"))])]}}])})],1),e._v(" "),n("el-dialog",{attrs:{title:e.resourceName,visible:e.resourceFormVisible},on:{"update:visible":function(t){e.resourceFormVisible=t}}},[n("el-tree",{ref:"tree",attrs:{data:e.data2,"show-checkbox":"","node-key":"id","default-checked-keys":e.checkedlist,props:e.defaultProps}}),e._v(" "),n("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[n("el-button",{on:{click:function(t){e.resourceFormVisible=!1}}},[e._v(e._s("取消"))]),e._v(" "),n("el-button",{attrs:{type:"primary"},on:{click:function(t){e.updateResource()}}},[e._v(e._s("确认"))])],1)],1)],1)},[],!1,null,null,null);p.options.__file="resourceTable.vue";t.default=p.exports},X9ZH:function(e,t,n){},ZySA:function(e,t,n){"use strict";var a=n("P2sY"),i=n.n(a),o=(n("jUE0"),{bind:function(e,t){e.addEventListener("click",function(n){var a=i()({},t.value),o=i()({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},a),r=o.ele;if(r){r.style.position="relative",r.style.overflow="hidden";var l=r.getBoundingClientRect(),s=r.querySelector(".waves-ripple");switch(s?s.className="waves-ripple":((s=document.createElement("span")).className="waves-ripple",s.style.height=s.style.width=Math.max(l.width,l.height)+"px",r.appendChild(s)),o.type){case"center":s.style.top=l.height/2-s.offsetHeight/2+"px",s.style.left=l.width/2-s.offsetWidth/2+"px";break;default:s.style.top=(n.pageY-l.top-s.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",s.style.left=(n.pageX-l.left-s.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return s.style.backgroundColor=o.color,s.className="waves-ripple z-active",!1}},!1)}}),r=function(e){e.directive("waves",o)};window.Vue&&(window.waves=o,Vue.use(r)),o.install=r;t.a=o},jUE0:function(e,t,n){}}]);
(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-c268"],{ZySA:function(e,t,a){"use strict";var n=a("P2sY"),i=a.n(n),l=(a("jUE0"),{bind:function(e,t){e.addEventListener("click",function(a){var n=i()({},t.value),l=i()({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},n),s=l.ele;if(s){s.style.position="relative",s.style.overflow="hidden";var o=s.getBoundingClientRect(),r=s.querySelector(".waves-ripple");switch(r?r.className="waves-ripple":((r=document.createElement("span")).className="waves-ripple",r.style.height=r.style.width=Math.max(o.width,o.height)+"px",s.appendChild(r)),l.type){case"center":r.style.top=o.height/2-r.offsetHeight/2+"px",r.style.left=o.width/2-r.offsetWidth/2+"px";break;default:r.style.top=(a.pageY-o.top-r.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",r.style.left=(a.pageX-o.left-r.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return r.style.backgroundColor=l.color,r.className="waves-ripple z-active",!1}},!1)}}),s=function(e){e.directive("waves",l)};window.Vue&&(window.waves=l,Vue.use(s)),l.install=s;t.a=l},jUE0:function(e,t,a){},lvce:function(e,t,a){"use strict";a.r(t);var n=a("t3Un");var i=a("ZySA"),l=(a("7Qib"),{directives:{waves:i.a},filters:{statusFilter:function(e){return{published:"success",draft:"gray",deleted:"danger"}[e]}},data:function(){return{secondary_name:"",secondary_channel:"",hidlist:"",pickerOptions0:{disabledDate:function(e){return e.getTime()>Date.now()-864e4}},directives:{waves:i.a},downloadLoading:!1,layout:"",timevalue:"",searchName:"搜索",tableKey:0,list:null,total:0,listLoading:!1,listQuery:{importance:void 0,title:void 0,type:void 0},listParam:{start:"",end:""},importanceOptions:[1,2,3],names:[],sortOptions:[{label:"ID Ascending",key:"+id"},{label:"ID Descending",key:"-id"}],statusOptions:["published","draft","deleted"]}},methods:{getDatawith:function(){if(this.listLoading=!0,this.hidlist!==[]){var e=[];this.listLoading=!0;var t=this.secondary_name;if(""==t||null==t)e=this.hidlist;else for(var a=0;a<this.hidlist.length;a++)-1!=this.hidlist[a].app_name.search(t)&&e.push(this.hidlist[a]);var n=[],i=this.secondary_channel;if(""==i)n=e;else for(var l=0;l<e.length;l++)-1!=e[l].channel.search(i)&&n.push(e[l]);this.list=n,this.listLoading=!1}},handleFilter:function(){var e=this;if(this.downloadLoading=!0,""===this.timevalue||null==this.timevalue||"undefined"===this.timevalue[0]||"undefined"===this.timevalue[1])return this.$message({message:"请选择查询范围~",type:"warning"}),void(this.downloadLoading=!1);this.listParam.start=this.timevalue[0],this.listParam.end=this.timevalue[1],this.listLoading=!0;var t=this;(function(e){return Object(n.a)({url:"/userdata/"+e.start+"/"+e.end,method:"get"})})(this.listParam).then(function(t){e.list=t.data,e.hidlist=t.data,e.getDatawith(),e.listLoading=!1,e.downloadLoading=!1}).catch(function(e){t.listLoading=!1,t.downloadLoading=!1})},formatJson:function(e,t){return t.map(function(t){return e.map(function(e){return t[e]})})}}}),s=a("KHd+"),o=Object(s.a)(l,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container",staticStyle:{margin:"15px","margin-top":"-5px"}},[a("el-date-picker",{attrs:{type:"daterange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期","value-format":"yyyy-MM-dd","picker-options":e.pickerOptions0},model:{value:e.timevalue,callback:function(t){e.timevalue=t},expression:"timevalue"}},[e._v(">\n      ")]),e._v(" "),a("el-button",{staticClass:"filter-item",attrs:{loading:e.downloadLoading,type:"primary",icon:"el-icon-search"},on:{click:e.handleFilter}},[e._v(e._s(e.searchName))]),e._v(" "),a("el-input",{staticClass:"filter-item",staticStyle:{width:"200px","margin-left":"15px","margin-bottom":"10px","margin-top":"-10px"},attrs:{placeholder:"根据游戏名二次筛选",clearable:""},on:{blur:e.getDatawith},model:{value:e.secondary_name,callback:function(t){e.secondary_name=t},expression:"secondary_name"}}),e._v(" "),a("el-input",{staticClass:"filter-item",staticStyle:{width:"200px","margin-left":"15px","margin-bottom":"10px","margin-top":"-10px"},attrs:{placeholder:"根据渠道二次筛选",clearable:""},on:{blur:e.getDatawith},model:{value:e.secondary_channel,callback:function(t){e.secondary_channel=t},expression:"secondary_channel"}})],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],attrs:{height:"850",data:e.list,"element-loading-text":"Loading",border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{align:"center",label:"ID",width:"95"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(++t.$index)+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"时间",width:"110",align:"center",prop:"date"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.date))])]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"游戏名",width:"110",align:"center",prop:"app_name"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(t.row.app_name)+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"渠道",prop:"channel"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.channel))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"日新增用户",prop:"advertising_type"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.dnu))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"日活跃用户",prop:"earned"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.dau))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"日启动次数",prop:"clickrate"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.startup_time))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"单次启动时长",prop:"ecpm"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.single_use_time))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"留存率",prop:"click"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.retention))])]}}])})],1)],1)},[],!1,null,null,null);o.options.__file="userdata.vue";t.default=o.exports}}]);
(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-c25e"],{ZySA:function(e,t,n){"use strict";var a=n("P2sY"),l=n.n(a),i=(n("jUE0"),{bind:function(e,t){e.addEventListener("click",function(n){var a=l()({},t.value),i=l()({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},a),o=i.ele;if(o){o.style.position="relative",o.style.overflow="hidden";var s=o.getBoundingClientRect(),r=o.querySelector(".waves-ripple");switch(r?r.className="waves-ripple":((r=document.createElement("span")).className="waves-ripple",r.style.height=r.style.width=Math.max(s.width,s.height)+"px",o.appendChild(r)),i.type){case"center":r.style.top=s.height/2-r.offsetHeight/2+"px",r.style.left=s.width/2-r.offsetWidth/2+"px";break;default:r.style.top=(n.pageY-s.top-r.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",r.style.left=(n.pageX-s.left-r.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return r.style.backgroundColor=i.color,r.className="waves-ripple z-active",!1}},!1)}}),o=function(e){e.directive("waves",i)};window.Vue&&(window.waves=i,Vue.use(o)),i.install=o;t.a=i},jUE0:function(e,t,n){},xUov:function(e,t,n){"use strict";n.r(t);var a=n("t3Un");var l=n("ZySA"),i=(n("7Qib"),{directives:{waves:l.a},filters:{statusFilter:function(e){return{published:"success",draft:"gray",deleted:"danger"}[e]}},data:function(){return{fullscreenLoading:!1,options:"",select_value:"",pickerOptions0:{disabledDate:function(e){return e.getTime()>Date.now()-864e4}},directives:{waves:l.a},downloadLoading:!1,layout:"",timevalue:"",searchName:"搜索",tableKey:0,list:null,total:0,listLoading:!1,listQuery:{importance:void 0,title:void 0,type:void 0},listParam:{start:"",end:"",name:"",appkey:""},importanceOptions:[1,2,3],names:[],sortOptions:[{label:"ID Ascending",key:"+id"},{label:"ID Descending",key:"-id"}],statusOptions:["published","draft","deleted"]}},mounted:function(){this.initapps()},methods:{initapps:function(){var e=this,t=this;t.fullscreenLoading=!0,Object(a.a)({url:"/umengapp",method:"get"}).then(function(n){e.options=n.data.data,t.fullscreenLoading=!1}).catch(function(e){t.$notify({title:"初始化app列表",message:"刷新试试",type:"error",duration:2e3}),t.fullscreenLoading=!1})},handleFilter:function(){var e=this,t=this;if(this.downloadLoading=!0,""===this.timevalue||null==this.timevalue||"undefined"===this.timevalue[0]||"undefined"===this.timevalue[1]||""===this.select_value)return this.$message({message:"记得选择查询范围~",type:"warning"}),void(this.downloadLoading=!1);var n=new Date(this.timevalue[0]).getTime();if(new Date(this.timevalue[1]).getTime()-n>5184e5)return t.$notify({title:"",message:"时间范围最多选择7天",type:"warning",duration:2e3}),void(this.downloadLoading=!1);this.listParam.start=this.timevalue[0],this.listParam.end=this.timevalue[1],this.listParam.name=this.select_value.name,this.listParam.appkey=this.select_value.appkey,this.listLoading=!0,function(e){return Object(a.a)({url:"/umeng",method:"post",data:e})}(this.listParam).then(function(t){for(var n=[],a=t.data.data,l=t.data.channel,i=0;i<a.length;i++)for(var o=a[i].app_name,s=a[i].list,r=0;r<s.length;r++){var d=s[r];d.id=o,void 0===d.launch&&(d.launch="--"),void 0===d.duration&&(d.duration="--"),d=e.setretention(d,l),n.push(d)}e.list=n,e.listLoading=!1,e.downloadLoading=!1}).catch(function(e){console.error(e),t.listLoading=!1,t.downloadLoading=!1})},setretention:function(e,t){for(var n=0;n<t.length;n++){var a=t[n].channelName,l=t[n].list;if(e.channel===a)for(var i=0;i<l.length;i++)if(l[i].install_period===e.date)return e.total_install=l[i].retention_rate[0],console.log(e),e.total_install_rate=0,e}return e.total_install=0,e.total_install_rate=0,e},handleDownload:function(){var e=this;if(this.downloadLoading=!0,null===this.list)return this.$message({message:"没有信息可以打印~",type:"warning"}),void(this.downloadLoading=!1);Promise.all([n.e("chunk-0d49"),n.e("chunk-92b8")]).then(n.bind(null,"S/jZ")).then(function(t){var n=e.formatJson(["date","id","channel","install","active_user","launch","duration","total_install","total_install_rate"],e.list);t.export_json_to_excel({header:["时间","应用名","渠道","新增人数","活跃人数","启动次数","单次启动时长","次日留存率","版本"],data:n,filename:"友盟3"}),e.downloadLoading=!1})},formatJson:function(e,t){return t.map(function(t){return e.map(function(e){return t[e]})})},formatDate:function(e,t){/(y+)/.test(t)&&(t=t.replace(RegExp.$1,(e.getFullYear()+"").substr(4-RegExp.$1.length)));var n={"M+":e.getMonth()+1,"d+":e.getDate(),"h+":e.getHours(),"m+":e.getMinutes(),"s+":e.getSeconds()};for(var a in n)if(new RegExp("("+a+")").test(t)){var l=n[a]+"";t=t.replace(RegExp.$1,1===RegExp.$1.length?l:this.padLeftZero(l))}return t},padLeftZero:function(e){return("00"+e).substr(e.length)}}}),o=n("KHd+"),s=Object(o.a)(i,function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"app-container"},[n("div",{staticClass:"filter-container",staticStyle:{margin:"15px","margin-top":"-5px"}},[n("el-date-picker",{attrs:{type:"daterange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期","value-format":"yyyy-MM-dd","picker-options":e.pickerOptions0},model:{value:e.timevalue,callback:function(t){e.timevalue=t},expression:"timevalue"}},[e._v(">\n    ")]),e._v(" "),n("el-select",{attrs:{filterable:"",placeholder:"请选择应用","value-key":"name"},model:{value:e.select_value,callback:function(t){e.select_value=t},expression:"select_value"}},e._l(e.options,function(t){return n("el-option",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.fullscreenLoading,expression:"fullscreenLoading",modifiers:{fullscreen:!0,lock:!0}}],key:t.appkey,attrs:{label:t.name,value:t}})})),e._v(" "),n("el-button",{staticClass:"filter-item",attrs:{loading:e.downloadLoading,type:"primary",icon:"el-icon-search"},on:{click:e.handleFilter}},[e._v(e._s(e.searchName)+"\n    ")]),e._v(" "),n("el-button",{directives:[{name:"waves",rawName:"v-waves"}],attrs:{loading:e.downloadLoading,type:"primary",icon:"el-icon-download"},on:{click:e.handleDownload}},[e._v("\n      "+e._s("下载")+"\n    ")])],1),e._v(" "),n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],attrs:{height:"850",data:e.list,"element-loading-text":"Loading",border:"",fit:"","highlight-current-row":""}},[n("el-table-column",{attrs:{align:"center",label:"ID",width:"95"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(++t.$index)+"\n      ")]}}])}),e._v(" "),n("el-table-column",{attrs:{label:"时间",width:"110",align:"center",prop:"date"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("span",[e._v(e._s(t.row.date))])]}}])}),e._v(" "),n("el-table-column",{attrs:{align:"center",label:"一天后",prop:"id"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("span",[e._v(e._s(t.row.oneDay))])]}}])}),e._v(" "),n("el-table-column",{attrs:{align:"center",label:"两天后",prop:"id"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("span",[e._v(e._s(t.row.twoDay))])]}}])}),e._v(" "),n("el-table-column",{attrs:{align:"center",label:"三天后",prop:"id"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("span",[e._v(e._s(t.row.threeDay))])]}}])}),e._v(" "),n("el-table-column",{attrs:{align:"center",label:"四天后",prop:"id"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("span",[e._v(e._s(t.row.fourDay))])]}}])}),e._v(" "),n("el-table-column",{attrs:{align:"center",label:"五天后",prop:"id"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("span",[e._v(e._s(t.row.fiveDay))])]}}])}),e._v(" "),n("el-table-column",{attrs:{align:"center",label:"六天后",prop:"id"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("span",[e._v(e._s(t.row.sixDay))])]}}])}),e._v(" "),n("el-table-column",{attrs:{align:"center",label:"七天后",prop:"id"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("span",[e._v(e._s(t.row.sevenDay))])]}}])}),e._v(" "),n("el-table-column",{attrs:{align:"center",label:"半月后（14天）",prop:"id"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("span",[e._v(e._s(t.row.halfmonth))])]}}])}),e._v(" "),n("el-table-column",{attrs:{align:"center",label:"一个月后（30天）",prop:"id"},scopedSlots:e._u([{key:"default",fn:function(t){return[n("span",[e._v(e._s(t.row.onemonth))])]}}])})],1)],1)},[],!1,null,null,null);s.options.__file="umengLifeTime.vue";t.default=s.exports}}]);
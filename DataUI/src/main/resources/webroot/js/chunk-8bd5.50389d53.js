(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-8bd5"],{"41Be":function(e,t,a){"use strict";a.d(t,"a",function(){return l});var n=a("Q2AE");function l(e){if(e&&e instanceof Array&&e.length>0){var t=e;return!!(n.a.getters&&n.a.getters.roles).some(function(e){return t.includes(e)})}return!1}},ZySA:function(e,t,a){"use strict";var n=a("P2sY"),l=a.n(n),o=(a("jUE0"),{bind:function(e,t){e.addEventListener("click",function(a){var n=l()({},t.value),o=l()({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},n),i=o.ele;if(i){i.style.position="relative",i.style.overflow="hidden";var s=i.getBoundingClientRect(),r=i.querySelector(".waves-ripple");switch(r?r.className="waves-ripple":((r=document.createElement("span")).className="waves-ripple",r.style.height=r.style.width=Math.max(s.width,s.height)+"px",i.appendChild(r)),o.type){case"center":r.style.top=s.height/2-r.offsetHeight/2+"px",r.style.left=s.width/2-r.offsetWidth/2+"px";break;default:r.style.top=(a.pageY-s.top-r.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",r.style.left=(a.pageX-s.left-r.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return r.style.backgroundColor=o.color,r.className="waves-ripple z-active",!1}},!1)}}),i=function(e){e.directive("waves",o)};window.Vue&&(window.waves=o,Vue.use(i)),o.install=i;t.a=o},jUE0:function(e,t,a){},"wyc/":function(e,t,a){"use strict";a.r(t);var n=a("QbLZ"),l=a.n(n),o=a("t3Un");var i=a("ZySA"),s=a("7Qib"),r=a("41Be"),d=a("L2JU"),c={directives:{waves:i.a},computed:l()({},Object(d.b)(["name","roles"])),filters:{statusFilter:function(e){return{published:"success",draft:"gray",deleted:"danger"}[e]}},data:function(){return{secondary_platform:"",secondary_date:"",secondary_channel:"",secondary_adtype:"",secondary_datelist:[],hidlist:[],options:[{value:"全部",label:"全部"},{value:"横幅",label:"横幅"},{value:"开屏",label:"开屏"},{value:"插屏",label:"插屏"},{value:"视频",label:"视频"}],pickerOptions0:{disabledDate:function(e){return e.getTime()>Date.now()-864e4}},directives:{waves:i.a},downloadLoading:!1,layout:"",chooseName:"选择游戏",chooseNamed:"选择游戏",select_value:"",download_value:"",downloadParam:{start:void 0,end:void 0,name:void 0},searchName:"搜索",tableKey:0,list:null,total:0,listLoading:!0,listQuery:{importance:void 0,title:void 0,type:void 0},listParam:{start:void 0,end:void 0,name:void 0},importanceOptions:[1,2,3],names:[],sortOptions:[{label:"ID Ascending",key:"+id"},{label:"ID Descending",key:"-id"}],statusOptions:["published","draft","deleted"]}},created:function(){this.fetchName()},methods:{open3:function(){this.$message({message:"请选择查询范围~",type:"warning"})},open4:function(){this.$message({message:"没有信息可以打印~",type:"warning"})},fetchName:function(){var e=this;if(Object(r.a)(["admin","leader","operator"])){var t=this;this.listLoading=!0,Object(o.a)({url:"/names",method:"get"}).then(function(t){e.names=t.data,e.listLoading=!1}).catch(function(e){t.listLoading=!1,console.log(e)})}else{var a={username:this.name},n=this;this.listLoading=!0,function(e){return Object(o.a)({url:"/api/getresourcelist",method:"post",params:e})}(a).then(function(t){e.names=t.data,e.listLoading=!1}).catch(function(e){n.listLoading=!1,console.error(e)})}},handleFilter:function(){var e=this;this.downloadLoading=!0;var t=this;if(this.listParam.name=this.chooseName,"选择游戏"===this.listParam.name||""===this.select_value||null==this.select_value)return this.open3(),void(this.downloadLoading=!1);this.listParam.start=this.select_value[0],this.listParam.end=this.select_value[1],this.listLoading=!0,function(e){return Object(o.a)({url:"/data/"+e.start+"_"+e.end+"_"+e.name,method:"get"})}(this.listParam).then(function(t){e.list=t.data,e.hidlist=t.data,e.listLoading=!1,e.downloadLoading=!1}).catch(function(e){t.listLoading=!1,t.downloadLoading=!1})},getDatawithName:function(){if(this.hidlist!==[]){var e=[];this.listLoading=!0;var t=this.secondary_adtype;if(""==t||null==t)e=this.hidlist;else for(var a=0;a<this.hidlist.length;a++)-1!=this.hidlist[a].advertising_type.search(t)&&e.push(this.hidlist[a]);var n=[],l=this.secondary_channel;if(""==l)n=e;else for(var o=0;o<e.length;o++)-1!=e[o].channel.search(l)&&n.push(e[o]);var i=[],s=this.secondary_date;if(""==s||"全部"==s)i=n;else for(var r=0;r<n.length;r++)-1!=n[r].date.search(s)&&i.push(n[r]);var d=[],c=this.secondary_platform;if(""==c||null==c)d=i;else{console.log(11);for(var u=0;u<i.length;u++)-1!=i[u].platform.search(c)&&d.push(i[u])}this.list=d,this.listLoading=!1}},handleDownloadAll:function(){var e=this;this.downloadLoading=!0;var t=this;this.downloadParam.start=this.download_value[0],this.downloadParam.end=this.download_value[1],this.downloadParam.name=this.chooseNamed,"选择游戏"!==this.downloadParam.name&&void 0!==this.downloadParam.start&&void 0!==this.downloadParam.end?function(e){return Object(o.a)({url:"/file/"+e.start+"_"+e.end+"_"+e.name,method:"get",responseType:"blob"})}(this.downloadParam).then(function(t){if(t){var a=window.URL.createObjectURL(new Blob([t])),n=document.createElement("a");n.style.display="none",n.href=a,n.setAttribute("download",e.downloadParam.start+"_"+e.downloadParam.end+"_"+e.downloadParam.name+".xls"),document.body.appendChild(n),n.click(),e.downloadLoading=!1}}).catch(function(e){t.downloadLoading=!1,t.$notify({title:"下载失败",message:"权限不足",type:"error",duration:2e3})}):this.open3()},handleDownload:function(){var e=this;if(this.downloadLoading=!0,null===this.list)return this.open4(),void(this.downloadLoading=!1);Promise.all([a.e("chunk-0d49"),a.e("chunk-fc87")]).then(a.bind(null,"S/jZ")).then(function(t){var a=e.formatJson(["id","date","app_name","channel","advertising_type","earned","click_rate","ecpm","impression","click","fill_rate","platform"],e.list);t.export_json_to_excel({header:["id","date","app_name","channel","advertising_type","earned","click_rate","ecpm","impression","click","fill_rate","platform"],data:a,filename:"table-list"}),e.downloadLoading=!1})},secondart_date_change:function(){null!==this.select_value&&(this.secondary_datelist=this.getdate(new Date(this.select_value[0]),new Date(this.select_value[1])))},getdate:function(e,t){var a=[];for(a.push("全部"),a.push(this.formatDate(e,"yyyy-MM-dd"));e.setDate(e.getDate()+1),e.getTime()<t.getTime();)a.push(this.formatDate(e,"yyyy-MM-dd"));return a.includes(this.formatDate(t,"yyyy-MM-dd"))||a.push(this.formatDate(t,"yyyy-MM-dd")),a},formatJson:function(e,t){return t.map(function(t){return e.map(function(e){return t[e]})})},checkPermission:r.a,parseTime:s.c,formatDate:function(e,t){/(y+)/.test(t)&&(t=t.replace(RegExp.$1,(e.getFullYear()+"").substr(4-RegExp.$1.length)));var a={"M+":e.getMonth()+1,"d+":e.getDate(),"h+":e.getHours(),"m+":e.getMinutes(),"s+":e.getSeconds()};for(var n in a)if(new RegExp("("+n+")").test(t)){var l=a[n]+"";t=t.replace(RegExp.$1,1===RegExp.$1.length?l:this.padLeftZero(l))}return t},padLeftZero:function(e){return("00"+e).substr(e.length)}}},u=a("KHd+"),p=Object(u.a)(c,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container",staticStyle:{margin:"15px","margin-top":"-5px"}},[a("el-date-picker",{attrs:{type:"daterange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期","value-format":"yyyy-MM-dd","picker-options":e.pickerOptions0},on:{change:e.secondart_date_change},model:{value:e.select_value,callback:function(t){e.select_value=t},expression:"select_value"}}),e._v(" "),a("el-select",{staticClass:"filter-item",staticStyle:{width:"140px"},attrs:{placeholder:"选择游戏"},model:{value:e.chooseName,callback:function(t){e.chooseName=t},expression:"chooseName"}},e._l(e.names,function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})})),e._v(" "),a("el-button",{directives:[{name:"waves",rawName:"v-waves"}],staticClass:"filter-item",attrs:{loading:e.downloadLoading,type:"primary",icon:"el-icon-search"},on:{click:e.handleFilter}},[e._v(e._s(e.searchName))]),e._v(" "),a("el-button",{directives:[{name:"waves",rawName:"v-waves"}],attrs:{loading:e.downloadLoading,type:"primary",icon:"el-icon-download"},on:{click:e.handleDownload}},[e._v(e._s("下载"))]),a("br")],1),e._v(" "),a("div",{staticStyle:{"margin-top":"-10px"}},[a("el-input",{staticClass:"filter-item",staticStyle:{width:"200px","margin-left":"15px","margin-bottom":"10px","margin-top":"-10px"},attrs:{placeholder:"根据渠道二次筛选",clearable:""},on:{blur:e.getDatawithName},model:{value:e.secondary_channel,callback:function(t){e.secondary_channel=t},expression:"secondary_channel"}}),e._v(" "),a("el-select",{attrs:{placeholder:"根据广告类型二次筛选"},on:{change:e.getDatawithName},model:{value:e.secondary_adtype,callback:function(t){e.secondary_adtype=t},expression:"secondary_adtype"}},e._l(e.options,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),e._v(" "),a("el-select",{staticStyle:{width:"170px"},attrs:{placeholder:"根据时间二次筛选"},on:{change:e.getDatawithName},model:{value:e.secondary_date,callback:function(t){e.secondary_date=t},expression:"secondary_date"}},e._l(e.secondary_datelist,function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})})),e._v(" "),a("el-input",{staticClass:"filter-item",staticStyle:{width:"200px","margin-bottom":"10px","margin-top":"-10px"},attrs:{placeholder:"根据平台二次筛选",clearable:""},on:{blur:e.getDatawithName},model:{value:e.secondary_platform,callback:function(t){e.secondary_platform=t},expression:"secondary_platform"}})],1),e._v(" "),e.checkPermission(["admin","operator","leader"])?a("div",{staticClass:"filter-container",staticStyle:{margin:"15px","margin-top":"-5px"}},[a("el-date-picker",{attrs:{type:"daterange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期","value-format":"yyyy-MM-dd","picker-options":e.pickerOptions0},model:{value:e.download_value,callback:function(t){e.download_value=t},expression:"download_value"}},[e._v(">\n  ")]),e._v(" "),a("el-select",{staticClass:"filter-item",staticStyle:{width:"140px"},attrs:{placeholder:"选择游戏"},model:{value:e.chooseNamed,callback:function(t){e.chooseNamed=t},expression:"chooseNamed"}},e._l(e.names,function(e){return a("el-option",{key:e,attrs:{label:e,value:e}})})),e._v(" "),a("el-button",{directives:[{name:"waves",rawName:"v-waves"}],attrs:{loading:e.downloadLoading,type:"primary",icon:"el-icon-download"},on:{click:e.handleDownloadAll}},[e._v(e._s("下载总表"))]),a("br")],1):e._e(),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],attrs:{stripe:"",data:e.list,"element-loading-text":"Loading",border:"",fit:"",fixed:"","highlight-current-row":""}},[a("el-table-column",{attrs:{align:"center",label:"ID",width:"95"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(++t.$index)+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"时间",width:"110",align:"center",prop:"date"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.date))])]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"游戏名",width:"110",align:"center",prop:"app_name"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(t.row.app_name)+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"渠道",prop:"channel"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.channel))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"广告类型",prop:"advertising_type"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.advertising_type))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"收入",prop:"earned"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.earned))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"点击率",prop:"clickrate"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.click_rate))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"ecpm",prop:"ecpm"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.ecpm))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"展示次数",prop:"impression"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.impression))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"点击次数",prop:"click"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.click))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"填充率",prop:"fill_rate"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.fill_rate))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"备注",prop:"platform",width:"200"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.note))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"平台",prop:"platform"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.platform))])]}}])})],1)],1)},[],!1,null,null,null);p.options.__file="select.vue";t.default=p.exports}}]);
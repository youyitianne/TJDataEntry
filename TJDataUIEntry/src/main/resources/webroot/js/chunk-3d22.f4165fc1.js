(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-3d22"],{"41Be":function(t,e,s){"use strict";s.d(e,"a",function(){return p});var a=s("Q2AE");function p(t){if(t&&t instanceof Array&&t.length>0){var e=t;return!!(a.a.getters&&a.a.getters.roles).some(function(t){return e.includes(t)})}return!1}},BvFL:function(t,e,s){"use strict";s.d(e,"a",function(){return p});var a=s("t3Un");function p(t){return Object(a.a)({url:"/api/getresourcelist",method:"post",params:t})}},J9mJ:function(t,e,s){"use strict";s.r(e);var a=s("QbLZ"),p=s.n(a),n=s("41Be"),d=s("BvFL"),o=s("Q2AE"),i=s("t3Un");var u=s("ZySA"),l=s("7Qib"),r=s("L2JU"),h={directives:{waves:u.a},computed:p()({},Object(r.b)(["name","roles"])),filters:{statusFilter:function(t){return{published:"success",draft:"gray",deleted:"danger"}[t]}},data:function(){return{perviewList:[],activeName:"first",appList_choosed:[],daily_adtype_download_value:[],daily_adtype_chooseNamed:"选择项目",app_name_list:[],hackReset:!1,pickerOptions0:{disabledDate:function(t){return t.getTime()>Date.now()-864e4}},directives:{waves:u.a},downloadLoading:!1,list:null,listLoading:!0,names:[]}},created:function(){this.fetchProject()},methods:{daily_adtype_download:function(){var t=this,e=this;null!==this.daily_adtype_download_value?"选择项目"!==this.daily_adtype_chooseNamed&&void 0!==this.daily_adtype_download_value[0]&&void 0!==this.daily_adtype_download_value[1]?(this.downloadLoading=!0,function(t){return Object(i.a)({url:"/dailyadtype_preview",method:"post",data:t})}({start:this.daily_adtype_download_value[0],end:this.daily_adtype_download_value[1],list:this.daily_adtype_chooseNamed}).then(function(e){if(e){for(var s=t.initList(),a=e.data,p=[],n=0;n<s.length;n++){var d=[];d.push(s[n]);for(var o=0;o<a.length;o++)d.push(a[o][n]);p.push(d)}t.perviewList=p,t.downloadLoading=!1}}).catch(function(t){console.log(t.toString()),e.downloadLoading=!1,e.$notify({title:"预览失败",message:"刷新试试",type:"error",duration:2e3})})):this.open3():this.open3()},daily_adtype_download1:function(){var t=this,e=this;if(null!==this.daily_adtype_download_value)if(this.appList_choosed.length<1)this.open3();else if(void 0!==this.daily_adtype_download_value[0])if(void 0!==this.daily_adtype_download_value[1]){this.downloadLoading=!0;for(var s=function(s){var a={start:t.daily_adtype_download_value[0],end:t.daily_adtype_download_value[1],list:t.appList_choosed[s]};(function(t){return Object(i.a)({url:"/dailyadtype",method:"post",responseType:"blob",data:t})})(a).then(function(e){if(e){var s=window.URL.createObjectURL(new Blob([e])),p=document.createElement("a");p.style.display="none",p.href=s,p.setAttribute("download",a.list.project_name+"_"+a.start+"_"+a.end+"_arpu.xls"),document.body.appendChild(p),p.click(),t.downloadLoading=!1}}).catch(function(t){console.log(t.toString()),e.downloadLoading=!1,e.$notify({title:"下载失败",message:"刷新试试",type:"error",duration:2e3})})},a=0;a<this.appList_choosed.length;a++)s(a)}else this.open3();else this.open3();else this.open3()},fetchProject:function(){var t=this,e=this;this.listLoading=!0,Object(i.a)({url:"/projectList",method:"get"}).then(function(e){var s=e.data;if(t.checkPermission(["director"])||t.checkPermission(["admin"])||t.checkPermission(["operatorleader"])||t.checkPermission(["sdksuport"]))t.app_name_list=s;else{var a=[],p={username:o.a.getters&&o.a.getters.name};Object(d.a)(p).then(function(t){for(var e=t.data,p=0;p<s.length;p++)for(var n=0;n<e.length;n++)-1!=s[p].project_name.indexOf(e[n])&&a.push(s[p])}),t.app_name_list=a}}).catch(function(t){console.log(t),e.listLoading=!1})},getNum:function(t,e){var s=[];s.push(t);for(var a=t+1;a<=e;a++)s.push(a);return s},formatJson:function(t,e){return e.map(function(e){return t.map(function(t){return e[t]})})},checkPermission:n.a,parseTime:l.c,getdate:function(t,e){var s=[];for(s.push("全部"),s.push(this.formatDate(t,"yyyy-MM-dd"));t.setDate(t.getDate()+1),t.getTime()<e.getTime();)s.push(this.formatDate(t,"yyyy-MM-dd"));return s.includes(this.formatDate(e,"yyyy-MM-dd"))||s.push(this.formatDate(e,"yyyy-MM-dd")),s},formatDate:function(t,e){/(y+)/.test(e)&&(e=e.replace(RegExp.$1,(t.getFullYear()+"").substr(4-RegExp.$1.length)));var s={"M+":t.getMonth()+1,"d+":t.getDate(),"h+":t.getHours(),"m+":t.getMinutes(),"s+":t.getSeconds()};for(var a in s)if(new RegExp("("+a+")").test(e)){var p=s[a]+"";e=e.replace(RegExp.$1,1===RegExp.$1.length?p:this.padLeftZero(p))}return e},padLeftZero:function(t){return("00"+t).substr(t.length)},open3:function(){this.$message({message:"请选择查询范围~",type:"warning"})},open4:function(){this.$message({message:"没有信息可以打印~",type:"warning"})},initList:function(){var t=[];t.push("日期");var e="oppo";return t=this.setList(e,t),e="华为",t=this.setList(e,t),e="vivo",t=this.setList(e,t),e="4399",t=this.setList(e,t)},setList:function(t,e){return e.push(t+"- 总营收 "),e.push(t+"- 总dauarpu "),e.push(t+"- 启动次数 "),e.push(t+"- 单次使用时长 "),e.push(t+"- 新增 "),e.push(t+"- 活跃 "),e.push(t+"- 次留 "),e.push(t+"- 广点通营收 "),e.push(t+"- gdt- dauarpu "),e.push(t+"- gdt- 横幅-普通 营收 "),e.push(t+"- gdt- 横幅-普通 点击率 "),e.push(t+"- gdt- 横幅-普通 ecpm "),e.push(t+"- gdt- 横幅-普通 展示总次数 "),e.push(t+"- gdt- 横幅-普通 每用户展示次数 "),e.push(t+"- gdt- 插屏-普通 营收 "),e.push(t+"- gdt- 插屏-普通 点击率 "),e.push(t+"- gdt- 插屏-普通 ecpm "),e.push(t+"- gdt- 插屏-普通 展示总次数 "),e.push(t+"- gdt- 插屏-普通 每用户展示次数 "),e.push(t+"- gdt- 插屏-渲染 营收 "),e.push(t+"- gdt- 插屏-渲染 点击率 "),e.push(t+"- gdt- 插屏-渲染 ecpm "),e.push(t+"- gdt- 插屏-渲染 展示总次数 "),e.push(t+"- gdt- 插屏-渲染 每用户展示次数 "),e.push(t+"- gdt- 插屏-模版 营收 "),e.push(t+"- gdt- 插屏-模版 点击率 "),e.push(t+"- gdt- 插屏-模版 ecpm "),e.push(t+"- gdt- 插屏-模版 展示总次数 "),e.push(t+"- gdt- 插屏-模版 每用户展示次数 "),e.push(t+"- gdt- 开屏-普通 营收 "),e.push(t+"- gdt- 开屏-普通 点击率 "),e.push(t+"- gdt- 开屏-普通 ecpm "),e.push(t+"- gdt- 开屏-普通 展示总次数 "),e.push(t+"- gdt- 开屏-普通 每用户展示次数 "),e.push(t+"- gdt- 开屏-渲染 营收 "),e.push(t+"- gdt- 开屏-渲染 点击率 "),e.push(t+"- gdt- 开屏-渲染 ecpm "),e.push(t+"- gdt- 开屏-渲染 展示总次数 "),e.push(t+"- gdt- 开屏-渲染 每用户展示次数 "),e.push(t+"- gdt- 开屏-模版 营收 "),e.push(t+"- gdt- 开屏-模版 点击率 "),e.push(t+"- gdt- 开屏-模版 ecpm "),e.push(t+"- gdt- 开屏-模版 展示总次数 "),e.push(t+"- gdt- 开屏-模版 每用户展示次数 "),e.push(t+"- gdt- 视频-激励 营收 "),e.push(t+"- gdt- 视频-激励 点击率 "),e.push(t+"- gdt- 视频-激励 ecpm "),e.push(t+"- gdt- 视频-激励 展示总次数 "),e.push(t+"- gdt- 视频-激励 每用户展示次数 "),e.push(t+"- 渠道分成 "),e.push(t+"- qd- dauarpu "),e.push(t+"- qd- 横幅-普通 营收 "),e.push(t+"- qd- 横幅-普通 点击率 "),e.push(t+"- qd- 横幅-普通 ecpm "),e.push(t+"- qd- 横幅-普通 展示总次数 "),e.push(t+"- qd- 横幅-普通 每用户展示次数 "),e.push(t+"- qd- 插屏-普通 营收 "),e.push(t+"- qd- 插屏-普通 点击率 "),e.push(t+"- qd- 插屏-普通 ecpm "),e.push(t+"- qd- 插屏-普通 展示总次数 "),e.push(t+"- qd- 插屏-普通 每用户展示次数 "),e.push(t+"- qd- 插屏-渲染 营收 "),e.push(t+"- qd- 插屏-渲染 点击率 "),e.push(t+"- qd- 插屏-渲染 ecpm "),e.push(t+"- qd- 插屏-渲染 展示总次数 "),e.push(t+"- qd- 插屏-渲染 每用户展示次数 "),e.push(t+"- qd- 插屏-模版 营收 "),e.push(t+"- qd- 插屏-模版 点击率 "),e.push(t+"- qd- 插屏-模版 ecpm "),e.push(t+"- qd- 插屏-模版 展示总次数 "),e.push(t+"- qd- 插屏-模版 每用户展示次数 "),e.push(t+"- qd- 开屏-普通 营收 "),e.push(t+"- qd- 开屏-普通 点击率 "),e.push(t+"- qd- 开屏-普通 ecpm "),e.push(t+"- qd- 开屏-普通 展示总次数 "),e.push(t+"- qd- 开屏-普通 每用户展示次数 "),e.push(t+"- qd- 开屏-渲染 营收 "),e.push(t+"- qd- 开屏-渲染 点击率 "),e.push(t+"- qd- 开屏-渲染 ecpm "),e.push(t+"- qd- 开屏-渲染 展示总次数 "),e.push(t+"- qd- 开屏-渲染 每用户展示次数 "),e.push(t+"- qd- 开屏-模版 营收 "),e.push(t+"- qd- 开屏-模版 点击率 "),e.push(t+"- qd- 开屏-模版 ecpm "),e.push(t+"- qd- 开屏-模版 展示总次数 "),e.push(t+"- qd- 开屏-模版 每用户展示次数 "),e.push(t+"- qd- 视频-普通 营收 "),e.push(t+"- qd- 视频-普通 点击率 "),e.push(t+"- qd- 视频-普通 ecpm "),e.push(t+"- qd- 视频-普通 展示总次数 "),e.push(t+"- qd- 视频-普通 每用户展示次数 "),e.push(t+"- qd- 视频-激励 营收 "),e.push(t+"- qd- 视频-激励 点击率 "),e.push(t+"- qd- 视频-激励 ecpm "),e.push(t+"- qd- 视频-激励 展示总次数 "),e.push(t+"- qd- 视频-激励 每用户展示次数 "),e.push(t+"- 头条分成 "),e.push(t+"- tt- dauarpu "),e.push(t+"- tt- 横幅-普通 营收 "),e.push(t+"- tt- 横幅-普通 点击率 "),e.push(t+"- tt- 横幅-普通 ecpm "),e.push(t+"- tt- 横幅-普通 展示总次数 "),e.push(t+"- tt- 横幅-普通 每用户展示次数 "),e.push(t+"- tt- 插屏-普通 营收 "),e.push(t+"- tt- 插屏-普通 点击率 "),e.push(t+"- tt- 插屏-普通 ecpm "),e.push(t+"- tt- 插屏-普通 展示总次数 "),e.push(t+"- tt- 插屏-普通 每用户展示次数 "),e.push(t+"- tt- 插屏-渲染 营收 "),e.push(t+"- tt- 插屏-渲染 点击率 "),e.push(t+"- tt- 插屏-渲染 ecpm "),e.push(t+"- tt- 插屏-渲染 展示总次数 "),e.push(t+"- tt- 插屏-渲染 每用户展示次数 "),e.push(t+"- tt- 插屏-模版 营收 "),e.push(t+"- tt- 插屏-模版 点击率 "),e.push(t+"- tt- 插屏-模版 ecpm "),e.push(t+"- tt- 插屏-模版 展示总次数 "),e.push(t+"- tt- 插屏-模版 每用户展示次数 "),e.push(t+"- tt- 开屏-普通 营收 "),e.push(t+"- tt- 开屏-普通 点击率 "),e.push(t+"- tt- 开屏-普通 ecpm "),e.push(t+"- tt- 开屏-普通 展示总次数 "),e.push(t+"- tt- 开屏-普通 每用户展示次数 "),e.push(t+"- tt- 开屏-渲染 营收 "),e.push(t+"- tt- 开屏-渲染 点击率 "),e.push(t+"- tt- 开屏-渲染 ecpm "),e.push(t+"- tt- 开屏-渲染 展示总次数 "),e.push(t+"- tt- 开屏-渲染 每用户展示次数 "),e.push(t+"- tt- 开屏-模版 营收 "),e.push(t+"- tt- 开屏-模版 点击率 "),e.push(t+"- tt- 开屏-模版 ecpm "),e.push(t+"- tt- 开屏-模版 展示总次数 "),e.push(t+"- tt- 开屏-模版 每用户展示次数 "),e.push(t+"- tt- 视频-普通 营收 "),e.push(t+"- tt- 视频-普通 点击率 "),e.push(t+"- tt- 视频-普通 ecpm "),e.push(t+"- tt- 视频-普通 展示总次数 "),e.push(t+"- tt- 视频-普通 每用户展示次数 "),e.push(t+"- tt- 视频-激励 营收 "),e.push(t+"- tt- 视频-激励 点击率 "),e.push(t+"- tt- 视频-激励 ecpm "),e.push(t+"- tt- 视频-激励 展示总次数 "),e.push(t+"- tt- 视频-激励 每用户展示次数 "),e}}},c=(s("n0TZ"),s("KHd+")),m=Object(c.a)(h,function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"app-container"},[s("div",{staticClass:"filter-container",staticStyle:{margin:"15px","margin-top":"-5px"}},[s("el-tabs",{model:{value:t.activeName,callback:function(e){t.activeName=e},expression:"activeName"}},[s("el-tab-pane",{attrs:{label:"预览",name:"first"}},[s("el-date-picker",{attrs:{type:"daterange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期","value-format":"yyyy-MM-dd","picker-options":t.pickerOptions0},model:{value:t.daily_adtype_download_value,callback:function(e){t.daily_adtype_download_value=e},expression:"daily_adtype_download_value"}},[t._v(">\n        ")]),t._v(" "),s("el-select",{staticStyle:{"margin-right":"20px"},attrs:{"value-key":"project_name",placeholder:"选择项目",filterable:""},on:{change:function(t){}},model:{value:t.daily_adtype_chooseNamed,callback:function(e){t.daily_adtype_chooseNamed=e},expression:"daily_adtype_chooseNamed"}},t._l(t.app_name_list,function(t){return s("el-option",{key:t.project_name,attrs:{label:t.project_name,value:t}})})),t._v(" "),s("el-button",{directives:[{name:"waves",rawName:"v-waves"}],attrs:{loading:t.downloadLoading,type:"primary",icon:"el-icon-search"},on:{click:t.daily_adtype_download}},[t._v("\n          "+t._s("预览")+"\n        ")]),t._v(" "),s("table",{staticClass:"gridtable",staticStyle:{"margin-top":"20px"}},t._l(t.perviewList,function(e){return s("tr",t._l(e,function(e){return s("td",[t._v(t._s(e))])}))}))],1),t._v(" "),s("el-tab-pane",{attrs:{label:"下载新展次表",name:"second"}},[s("el-date-picker",{attrs:{type:"daterange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期","value-format":"yyyy-MM-dd","picker-options":t.pickerOptions0},model:{value:t.daily_adtype_download_value,callback:function(e){t.daily_adtype_download_value=e},expression:"daily_adtype_download_value"}},[t._v(">\n        ")]),t._v(" "),s("el-select",{staticStyle:{"margin-right":"20px"},attrs:{"value-key":"project_name",multiple:"","multiple-limit":5,"collapse-tags":"",placeholder:"选择项目",filterable:""},on:{change:function(t){}},model:{value:t.appList_choosed,callback:function(e){t.appList_choosed=e},expression:"appList_choosed"}},t._l(t.app_name_list,function(t){return s("el-option",{key:t.project_name,attrs:{label:t.project_name,value:t}})})),t._v(" "),s("el-button",{directives:[{name:"waves",rawName:"v-waves"}],attrs:{loading:t.downloadLoading,type:"primary",icon:"el-icon-download"},on:{click:t.daily_adtype_download1}},[t._v("\n          "+t._s("下载展次表（新）")+"\n        ")])],1)],1),t._v(" "),s("br")],1)])},[],!1,null,null,null);m.options.__file="showtime_2table.vue";e.default=m.exports},VDVt:function(t,e,s){},ZySA:function(t,e,s){"use strict";var a=s("P2sY"),p=s.n(a),n=(s("jUE0"),{bind:function(t,e){t.addEventListener("click",function(s){var a=p()({},e.value),n=p()({ele:t,type:"hit",color:"rgba(0, 0, 0, 0.15)"},a),d=n.ele;if(d){d.style.position="relative",d.style.overflow="hidden";var o=d.getBoundingClientRect(),i=d.querySelector(".waves-ripple");switch(i?i.className="waves-ripple":((i=document.createElement("span")).className="waves-ripple",i.style.height=i.style.width=Math.max(o.width,o.height)+"px",d.appendChild(i)),n.type){case"center":i.style.top=o.height/2-i.offsetHeight/2+"px",i.style.left=o.width/2-i.offsetWidth/2+"px";break;default:i.style.top=(s.pageY-o.top-i.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",i.style.left=(s.pageX-o.left-i.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return i.style.backgroundColor=n.color,i.className="waves-ripple z-active",!1}},!1)}}),d=function(t){t.directive("waves",n)};window.Vue&&(window.waves=n,Vue.use(d)),n.install=d;e.a=n},jUE0:function(t,e,s){},n0TZ:function(t,e,s){"use strict";var a=s("VDVt");s.n(a).a}}]);
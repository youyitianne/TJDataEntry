(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-4f4f"],{"3Gho":function(e,t,a){"use strict";a.d(t,"c",function(){return o}),a.d(t,"g",function(){return i}),a.d(t,"e",function(){return d}),a.d(t,"f",function(){return s}),a.d(t,"d",function(){return l}),a.d(t,"b",function(){return r}),a.d(t,"a",function(){return c});var n=a("t3Un");function o(){return Object(n.a)({url:"/names",method:"get"})}function i(e){return Object(n.a)({url:"/file",method:"post",data:e,responseType:"blob"})}function d(e){return Object(n.a)({url:"/api/getresourcelist",method:"post",params:e})}function s(e){return Object(n.a)({url:"/arpufile/"+e.start+"_"+e.end,method:"post",responseType:"blob",params:e,data:e})}function l(){return Object(n.a)({url:"/projectList",method:"get"})}function r(e){return Object(n.a)({url:"/daily",method:"post",responseType:"blob",data:e})}function c(e){return Object(n.a)({url:"/dailyadtype",method:"post",responseType:"blob",data:e})}},"41Be":function(e,t,a){"use strict";a.d(t,"a",function(){return o});var n=a("Q2AE");function o(e){if(e&&e instanceof Array&&e.length>0){var t=e;return!!(n.a.getters&&n.a.getters.roles).some(function(e){return t.includes(e)})}return!1}},"W+dE":function(e,t,a){"use strict";a.r(t);var n=a("QbLZ"),o=a.n(n),i=a("3Gho"),d=a("ZySA"),s=a("7Qib"),l=a("41Be"),r=a("L2JU"),c=a("t3Un");var u={directives:{waves:d.a},computed:o()({},Object(r.b)(["name","roles"])),filters:{statusFilter:function(e){return{published:"success",draft:"gray",deleted:"danger"}[e]}},data:function(){return{daily_download_value:[],app_name_list:[],secondary_project:"选择项目",hackReset:!1,pickerOptions0:{disabledDate:function(e){return e.getTime()>Date.now()-864e4}},directives:{waves:d.a},downloadLoading:!1,download_value:"",downloadParam:{start:void 0,end:void 0,name:void 0,namelist:"",list:[]},list:null,listLoading:!0,names:[]}},created:function(){this.fetchName(),this.fetchProject()},methods:{daily_download:function(){var e=this,t=this;if(null!==this.daily_download_value)if(void 0!==this.daily_download_value[0])if(void 0!==this.daily_download_value[1]){this.downloadLoading=!0;var a={start:this.daily_download_value[0],end:this.daily_download_value[1],list:this.app_name_list};Object(i.b)(a).then(function(t){if(t){var n=window.URL.createObjectURL(new Blob([t])),o=document.createElement("a");o.style.display="none",o.href=n,o.setAttribute("download",a.start+"_"+a.end+"_产品收益表.xls"),document.body.appendChild(o),o.click(),e.downloadLoading=!1}}).catch(function(e){console.log(e.toString()+"asdasdas"),t.downloadLoading=!1,t.$notify({title:"下载失败",message:"刷新试试",type:"error",duration:2e3})})}else this.open3();else this.open3();else this.open3()},daily_download1:function(){var e=this,t=this;null!==this.daily_download_value?void 0!==this.daily_download_value[0]&&void 0!==this.daily_download_value[1]?(this.downloadLoading=!0,function(e){return Object(c.a)({url:"/daily_preview",method:"post",data:e})}({start:this.daily_download_value[0],end:this.daily_download_value[1],list:this.app_name_list}).then(function(t){t&&(console.log(t),e.downloadLoading=!1)}).catch(function(e){console.log(e.toString()+"asdasdas"),t.downloadLoading=!1,t.$notify({title:"下载失败",message:"刷新试试",type:"error",duration:2e3})})):this.open3():this.open3()},fetchProject:function(){var e=this,t=this;this.listLoading=!0,Object(i.d)().then(function(t){e.app_name_list=t.data}).catch(function(e){console.log(e),t.listLoading=!1})},fetchName:function(){var e=this;if(Object(l.a)(["admin","leader","operator"])){var t=this;this.listLoading=!0,Object(i.c)().then(function(t){e.names=t.data,e.listLoading=!1}).catch(function(e){t.listLoading=!1,console.log(e)})}else{var a={username:this.name},n=this;this.listLoading=!0,Object(i.e)(a).then(function(t){e.names=t.data,e.listLoading=!1}).catch(function(e){n.listLoading=!1,console.error(e)})}},handleDownloadAll:function(){null!==this.download_value?(this.downloadParam.start=this.download_value[0],this.downloadParam.end=this.download_value[1],this.downloadParam.name=this.secondary_project,"选择项目"!==this.downloadParam.name&&void 0!==this.downloadParam.start&&void 0!==this.downloadParam.end?this.hackReset=!0:this.open3()):this.open3()},checkPermission:l.a,parseTime:s.c,getdate:function(e,t){var a=[];for(a.push("全部"),a.push(this.formatDate(e,"yyyy-MM-dd"));e.setDate(e.getDate()+1),e.getTime()<t.getTime();)a.push(this.formatDate(e,"yyyy-MM-dd"));return a.includes(this.formatDate(t,"yyyy-MM-dd"))||a.push(this.formatDate(t,"yyyy-MM-dd")),a},formatDate:function(e,t){/(y+)/.test(t)&&(t=t.replace(RegExp.$1,(e.getFullYear()+"").substr(4-RegExp.$1.length)));var a={"M+":e.getMonth()+1,"d+":e.getDate(),"h+":e.getHours(),"m+":e.getMinutes(),"s+":e.getSeconds()};for(var n in a)if(new RegExp("("+n+")").test(t)){var o=a[n]+"";t=t.replace(RegExp.$1,1===RegExp.$1.length?o:this.padLeftZero(o))}return t},padLeftZero:function(e){return("00"+e).substr(e.length)},open3:function(){this.$message({message:"请选择查询范围~",type:"warning"})}}},h=a("KHd+"),p=Object(h.a)(u,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[e.checkPermission(["admin","operatorleader","director"])?a("div",{staticClass:"filter-container",staticStyle:{margin:"15px","margin-top":"-5px"}},[a("el-date-picker",{attrs:{type:"daterange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期","value-format":"yyyy-MM-dd","picker-options":e.pickerOptions0},model:{value:e.daily_download_value,callback:function(t){e.daily_download_value=t},expression:"daily_download_value"}},[e._v(">\n    ")]),e._v(" "),a("el-button",{directives:[{name:"waves",rawName:"v-waves"}],attrs:{loading:e.downloadLoading,type:"primary",icon:"el-icon-download"},on:{click:e.daily_download}},[e._v("\n      "+e._s("下载收益表")+"\n    ")])],1):e._e()])},[],!1,null,null,null);p.options.__file="earnedtable.vue";t.default=p.exports},ZySA:function(e,t,a){"use strict";var n=a("P2sY"),o=a.n(n),i=(a("jUE0"),{bind:function(e,t){e.addEventListener("click",function(a){var n=o()({},t.value),i=o()({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},n),d=i.ele;if(d){d.style.position="relative",d.style.overflow="hidden";var s=d.getBoundingClientRect(),l=d.querySelector(".waves-ripple");switch(l?l.className="waves-ripple":((l=document.createElement("span")).className="waves-ripple",l.style.height=l.style.width=Math.max(s.width,s.height)+"px",d.appendChild(l)),i.type){case"center":l.style.top=s.height/2-l.offsetHeight/2+"px",l.style.left=s.width/2-l.offsetWidth/2+"px";break;default:l.style.top=(a.pageY-s.top-l.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",l.style.left=(a.pageX-s.left-l.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return l.style.backgroundColor=i.color,l.className="waves-ripple z-active",!1}},!1)}}),d=function(e){e.directive("waves",i)};window.Vue&&(window.waves=i,Vue.use(d)),i.install=d;t.a=i},jUE0:function(e,t,a){}}]);
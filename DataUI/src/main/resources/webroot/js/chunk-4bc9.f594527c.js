(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-4bc9"],{"3Gho":function(e,t,l){"use strict";l.d(t,"b",function(){return i}),l.d(t,"f",function(){return n}),l.d(t,"d",function(){return o}),l.d(t,"c",function(){return d}),l.d(t,"a",function(){return r}),l.d(t,"e",function(){return s});var a=l("t3Un");function i(){return Object(a.a)({url:"/names",method:"get"})}function n(e){return Object(a.a)({url:"/file",method:"post",data:e,responseType:"blob"})}function o(e){return Object(a.a)({url:"/api/getresourcelist",method:"post",params:e})}function d(){return Object(a.a)({url:"/projectList",method:"get"})}function r(e){return Object(a.a)({url:"/daily",method:"post",responseType:"blob",data:e})}function s(e){return Object(a.a)({url:"/arpufile_preview/"+e.start+"_"+e.end,method:"post",params:e,data:e})}},"41Be":function(e,t,l){"use strict";l.d(t,"a",function(){return i});var a=l("Q2AE");function i(e){if(e&&e instanceof Array&&e.length>0){var t=e;return!!(a.a.getters&&a.a.getters.roles).some(function(e){return t.includes(e)})}return!1}},BvFL:function(e,t,l){"use strict";l.d(t,"a",function(){return i});var a=l("t3Un");function i(e){return Object(a.a)({url:"/api/getresourcelist",method:"post",params:e})}},ZySA:function(e,t,l){"use strict";var a=l("P2sY"),i=l.n(a),n=(l("jUE0"),{bind:function(e,t){e.addEventListener("click",function(l){var a=i()({},t.value),n=i()({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},a),o=n.ele;if(o){o.style.position="relative",o.style.overflow="hidden";var d=o.getBoundingClientRect(),r=o.querySelector(".waves-ripple");switch(r?r.className="waves-ripple":((r=document.createElement("span")).className="waves-ripple",r.style.height=r.style.width=Math.max(d.width,d.height)+"px",o.appendChild(r)),n.type){case"center":r.style.top=d.height/2-r.offsetHeight/2+"px",r.style.left=d.width/2-r.offsetWidth/2+"px";break;default:r.style.top=(l.pageY-d.top-r.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",r.style.left=(l.pageX-d.left-r.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return r.style.backgroundColor=n.color,r.className="waves-ripple z-active",!1}},!1)}}),o=function(e){e.directive("waves",n)};window.Vue&&(window.waves=n,Vue.use(o)),n.install=o;t.a=n},jUE0:function(e,t,l){},kkmv:function(e,t,l){"use strict";l.r(t);var a=l("QbLZ"),i=l.n(a),n=l("41Be"),o=l("BvFL"),d=l("Q2AE"),r=l("3Gho"),s=l("ZySA"),c=l("7Qib"),u=l("L2JU"),b={directives:{waves:s.a},computed:i()({},Object(u.b)(["name","roles"])),filters:{statusFilter:function(e){return{published:"success",draft:"gray",deleted:"danger"}[e]}},data:function(){return{appList_choosed:[],daily_adtype_download_value:[],daily_adtype_chooseNamed:"选择项目",daily_download_value:[],app_name_list:[],secondary_project:"选择项目",data2:[{label:"oppo",children:[{id:"6+9",label:"统计"},{id:"10+29",label:"广点通"},{id:"30+54",label:"渠道"},{id:"55+79",label:"移信"},{id:"80+104",label:"头条"}]},{label:"小米",children:[{id:"105+108",label:"统计"},{id:"109+128",label:"广点通"},{id:"129+153",label:"渠道"},{id:"154+178",label:"移信"},{id:"179+203",label:"头条"}]},{label:"vivo",children:[{id:"204+207",label:"统计"},{id:"208+227",label:"广点通"},{id:"228+252",label:"渠道"},{id:"253+277",label:"移信"},{id:"278+302",label:"头条"}]},{label:"魅族",children:[{id:"303+306",label:"统计"},{id:"307+326",label:"广点通"},{id:"327+351",label:"渠道"},{id:"352+376",label:"移信"},{id:"377+401",label:"头条"}]},{label:"qihoo",children:[{id:"402+405",label:"统计"},{id:"406+425",label:"广点通"},{id:"426+450",label:"渠道"},{id:"451+475",label:"移信"},{id:"476+500",label:"头条"}]},{label:"三星",children:[{id:"501+504",label:"统计"},{id:"505+524",label:"广点通"},{id:"525+549",label:"渠道"},{id:"550+574",label:"移信"},{id:"575+599",label:"头条"}]},{label:"联想",children:[{id:"600+603",label:"统计"},{id:"604+623",label:"广点通"},{id:"624+648",label:"渠道"},{id:"649+673",label:"移信"},{id:"674+698",label:"头条"}]},{label:"九游",children:[{id:"699+702",label:"统计"},{id:"703+722",label:"广点通"},{id:"723+747",label:"渠道"},{id:"748+772",label:"移信"},{id:"773+797",label:"头条"}]},{label:"4399",children:[{id:"798+801",label:"统计"},{id:"802+821",label:"广点通"},{id:"822+846",label:"渠道"},{id:"847+871",label:"移信"},{id:"872+896",label:"头条"}]},{label:"应用汇",children:[{id:"897+900",label:"统计"},{id:"901+920",label:"广点通"},{id:"921+945",label:"渠道"},{id:"946+970",label:"移信"},{id:"971+995",label:"头条"}]},{label:"应用宝",children:[{id:"996+999",label:"统计"},{id:"1000+1019",label:"广点通"},{id:"1020+1044",label:"渠道"},{id:"1045+1069",label:"移信"},{id:"1070+1094",label:"头条"}]},{label:"百度",children:[{id:"1095+1098",label:"统计"},{id:"1099+1118",label:"广点通"},{id:"1119+1143",label:"渠道"},{id:"1144+1168",label:"移信"},{id:"1169+1193",label:"头条"}]},{label:"酷派",children:[{id:"1194+1197",label:"统计"},{id:"1198+1217",label:"广点通"},{id:"1218+1242",label:"渠道"},{id:"1243+1267",label:"移信"},{id:"1268+1292",label:"头条"}]},{label:"金立游戏",children:[{id:"1293+1296",label:"统计"},{id:"1297+1316",label:"广点通"},{id:"1317+1341",label:"渠道"},{id:"1342+1366",label:"移信"},{id:"1367+1391",label:"头条"}]},{label:"华为",children:[{id:"1392+1395",label:"统计"},{id:"1396+1415",label:"广点通"},{id:"1416+1440",label:"渠道"},{id:"1441+1465",label:"移信"},{id:"1466+1490",label:"头条"}]},{label:"中兴",children:[{id:"1491+1494",label:"统计"},{id:"1495+1514",label:"广点通"},{id:"1515+1539",label:"渠道"},{id:"1540+1564",label:"移信"},{id:"1565+1589",label:"头条"}]},{label:"taptap",children:[{id:"1590+1593",label:"统计"},{id:"1594+1613",label:"广点通"},{id:"1614+1638",label:"渠道"},{id:"1639+1663",label:"移信"},{id:"1664+1688",label:"头条"}]},{label:"好游快爆",children:[{id:"1689+1692",label:"统计"},{id:"1693+1712",label:"广点通"},{id:"1713+1737",label:"渠道"},{id:"1738+1762",label:"移信"},{id:"1763+1787",label:"头条"}]}],hackReset:!1,options:[{value:"全部",label:"全部"},{value:"横幅",label:"横幅"},{value:"开屏",label:"开屏"},{value:"插屏",label:"插屏"},{value:"视频",label:"视频"}],pickerOptions0:{disabledDate:function(e){return e.getTime()>Date.now()-864e4}},directives:{waves:s.a},downloadLoading:!1,download_value:"",downloadParam:{start:void 0,end:void 0,name:void 0,namelist:"",list:[]},list:null,listLoading:!0,names:[]}},created:function(){this.fetchProject()},methods:{fetchProject:function(){var e=this,t=this;this.listLoading=!0,Object(r.c)().then(function(t){var l=t.data;console.log(t.data);var a=e.checkPermission(["director"])||e.checkPermission(["admin"])||e.checkPermission(["operatorleader"])||e.checkPermission(["sdksuport"]);if(console.log(a),a)e.app_name_list=l;else{var i=[],n={username:d.a.getters&&d.a.getters.name};Object(o.a)(n).then(function(e){for(var t=e.data,a=0;a<l.length;a++)for(var n=0;n<t.length;n++)-1!=l[a].project_name.indexOf(t[n])&&i.push(l[a])}),e.app_name_list=i}}).catch(function(e){console.log(e),t.listLoading=!1})},getnumlist:function(){var e=this;this.hackReset=!1,this.downloadLoading=!0;for(var t=this,l=this.$refs.tree.getCheckedKeys(),a=[],i=0;i<l.length;i++)if(void 0!==l[i]){var n=this.getNum(parseInt(l[i].split("+")[0]),parseInt(l[i].split("+")[1]));a=a.concat(n)}this.downloadParam.list=a,Object(r.f)(this.downloadParam).then(function(t){if(t){var l=window.URL.createObjectURL(new Blob([t])),a=document.createElement("a");a.style.display="none",a.href=l,a.setAttribute("download",e.downloadParam.name.project_name+"_"+e.downloadParam.start+"_"+e.downloadParam.end+"_.xls"),document.body.appendChild(a),a.click(),e.downloadLoading=!1}}).catch(function(e){t.downloadLoading=!1,t.$notify({title:"下载失败",message:"刷新试试",type:"error",duration:2e3})})},getNum:function(e,t){var l=[];l.push(e);for(var a=e+1;a<=t;a++)l.push(a);return l},chooseyixin:function(){this.$refs.tree.setCheckedKeys(["80+104","179+203","278+302","377+401","476+500","575+599","674+698","773+797","872+896","971+995","1070+1094","1169+1193","1268+1292","1367+1391","55+79","154+178","253+277","352+376","451+475","550+574","649+673","748+772","847+871","946+970","1045+1069","1144+1168","1243+1267","1342+1366","1441+1465","1540+1564","1639+1663","1738+1762","921+945","1020+1044","1218+1242","1317+1341","1416+1440","1515+1539","1614+1638","1713+1737","897+900","1194+1197","1293+1296","1392+1395"])},rechoose:function(){this.$refs.tree.setCheckedKeys([])},fetchName:function(){var e=this;if(Object(n.a)(["admin","leader","operator"])){var t=this;this.listLoading=!0,Object(r.b)().then(function(t){e.names=t.data,e.listLoading=!1}).catch(function(e){t.listLoading=!1,console.log(e)})}else{var l={username:this.name},a=this;this.listLoading=!0,Object(o.a)(l).then(function(t){e.names=t.data,e.listLoading=!1}).catch(function(e){a.listLoading=!1,console.error(e)})}},handleDownloadAll:function(){null!==this.download_value?(this.downloadParam.start=this.download_value[0],this.downloadParam.end=this.download_value[1],this.downloadParam.name=this.secondary_project,"选择项目"!==this.downloadParam.name&&void 0!==this.downloadParam.start&&void 0!==this.downloadParam.end?this.hackReset=!0:this.open3()):this.open3()},checkPermission:n.a,parseTime:c.c,getdate:function(e,t){var l=[];for(l.push("全部"),l.push(this.formatDate(e,"yyyy-MM-dd"));e.setDate(e.getDate()+1),e.getTime()<t.getTime();)l.push(this.formatDate(e,"yyyy-MM-dd"));return l.includes(this.formatDate(t,"yyyy-MM-dd"))||l.push(this.formatDate(t,"yyyy-MM-dd")),l},formatDate:function(e,t){/(y+)/.test(t)&&(t=t.replace(RegExp.$1,(e.getFullYear()+"").substr(4-RegExp.$1.length)));var l={"M+":e.getMonth()+1,"d+":e.getDate(),"h+":e.getHours(),"m+":e.getMinutes(),"s+":e.getSeconds()};for(var a in l)if(new RegExp("("+a+")").test(t)){var i=l[a]+"";t=t.replace(RegExp.$1,1===RegExp.$1.length?i:this.padLeftZero(i))}return t},padLeftZero:function(e){return("00"+e).substr(e.length)},open3:function(){this.$message({message:"请选择查询范围~",type:"warning"})},open4:function(){this.$message({message:"没有信息可以打印~",type:"warning"})}}},h=l("KHd+"),p=Object(h.a)(b,function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("div",{staticClass:"app-container"},[l("div",{staticClass:"filter-container",staticStyle:{margin:"15px","margin-top":"-5px"}},[l("el-date-picker",{attrs:{type:"daterange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期","value-format":"yyyy-MM-dd","picker-options":e.pickerOptions0},model:{value:e.download_value,callback:function(t){e.download_value=t},expression:"download_value"}},[e._v(">\n    ")]),e._v(" "),l("el-select",{staticStyle:{"margin-right":"20px"},attrs:{"value-key":"project_name",placeholder:"选择项目",filterable:""},model:{value:e.secondary_project,callback:function(t){e.secondary_project=t},expression:"secondary_project"}},e._l(e.app_name_list,function(e){return l("el-option",{key:e.project_name,attrs:{label:e.project_name,value:e}})})),e._v(" "),l("el-button",{directives:[{name:"waves",rawName:"v-waves"}],attrs:{loading:e.downloadLoading,type:"primary",icon:"el-icon-download"},on:{click:e.handleDownloadAll}},[e._v("\n      "+e._s("下载展次表（旧）")+"\n    ")]),e._v(" "),l("br")],1),e._v(" "),l("el-dialog",{attrs:{title:"选择需要去除的部分",visible:e.hackReset,width:"40%"},on:{"update:visible":function(t){e.hackReset=t}}},[l("el-button",{on:{click:function(t){e.chooseyixin()}}},[e._v("模板一")]),e._v(" "),l("el-button",{on:{click:function(t){e.rechoose()}}},[e._v("还原")]),e._v(" "),l("el-tree",{ref:"tree",attrs:{data:e.data2,"node-key":"id","show-checkbox":"","highlight-current":""}}),e._v(" "),l("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[l("el-button",{on:{click:function(t){e.hackReset=!1}}},[e._v(e._s("取消"))]),e._v(" "),l("el-button",{attrs:{type:"primary"},on:{click:e.getnumlist}},[e._v(e._s("确认"))])],1)],1)],1)},[],!1,null,null,null);p.options.__file="showtime_1table.vue";t.default=p.exports}}]);
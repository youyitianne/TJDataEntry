(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-d28b"],{"41Be":function(e,a,t){"use strict";t.d(a,"a",function(){return n});var l=t("Q2AE");function n(e){if(e&&e instanceof Array&&e.length>0){var a=e;return!!(l.a.getters&&l.a.getters.roles).some(function(e){return a.includes(e)})}return!1}},XLAA:function(e,a,t){"use strict";t.r(a);var l=t("QbLZ"),n=t.n(l),i=t("t3Un");var o=t("ZySA"),d=t("7Qib"),s=t("41Be"),r=t("L2JU"),c={directives:{waves:o.a},computed:n()({},Object(r.b)(["name","roles"])),filters:{statusFilter:function(e){return{published:"success",draft:"gray",deleted:"danger"}[e]}},data:function(){return{daily_adtype_download_value:[],daily_adtype_chooseNamed:"选择项目",daily_download_value:[],app_name_list:[],secondary_project:"选择项目",data2:[{label:"oppo",children:[{id:"6+9",label:"统计"},{id:"10+29",label:"广点通"},{id:"30+54",label:"渠道"},{id:"55+79",label:"移信"},{id:"80+104",label:"头条"}]},{label:"小米",children:[{id:"105+108",label:"统计"},{id:"109+128",label:"广点通"},{id:"129+153",label:"渠道"},{id:"154+178",label:"移信"},{id:"179+203",label:"头条"}]},{label:"vivo",children:[{id:"204+207",label:"统计"},{id:"208+227",label:"广点通"},{id:"228+252",label:"渠道"},{id:"253+277",label:"移信"},{id:"278+302",label:"头条"}]},{label:"魅族",children:[{id:"303+306",label:"统计"},{id:"307+326",label:"广点通"},{id:"327+351",label:"渠道"},{id:"352+376",label:"移信"},{id:"377+401",label:"头条"}]},{label:"qihoo",children:[{id:"402+405",label:"统计"},{id:"406+425",label:"广点通"},{id:"426+450",label:"渠道"},{id:"451+475",label:"移信"},{id:"476+500",label:"头条"}]},{label:"三星",children:[{id:"501+504",label:"统计"},{id:"505+524",label:"广点通"},{id:"525+549",label:"渠道"},{id:"550+574",label:"移信"},{id:"575+599",label:"头条"}]},{label:"联想",children:[{id:"600+603",label:"统计"},{id:"604+623",label:"广点通"},{id:"624+648",label:"渠道"},{id:"649+673",label:"移信"},{id:"674+698",label:"头条"}]},{label:"九游",children:[{id:"699+702",label:"统计"},{id:"703+722",label:"广点通"},{id:"723+747",label:"渠道"},{id:"748+772",label:"移信"},{id:"773+797",label:"头条"}]},{label:"4399",children:[{id:"798+801",label:"统计"},{id:"802+821",label:"广点通"},{id:"822+846",label:"渠道"},{id:"847+871",label:"移信"},{id:"872+896",label:"头条"}]},{label:"应用汇",children:[{id:"897+900",label:"统计"},{id:"901+920",label:"广点通"},{id:"921+945",label:"渠道"},{id:"946+970",label:"移信"},{id:"971+995",label:"头条"}]},{label:"应用宝",children:[{id:"996+999",label:"统计"},{id:"1000+1019",label:"广点通"},{id:"1020+1044",label:"渠道"},{id:"1045+1069",label:"移信"},{id:"1070+1094",label:"头条"}]},{label:"百度",children:[{id:"1095+1098",label:"统计"},{id:"1099+1118",label:"广点通"},{id:"1119+1143",label:"渠道"},{id:"1144+1168",label:"移信"},{id:"1169+1193",label:"头条"}]},{label:"酷派",children:[{id:"1194+1197",label:"统计"},{id:"1198+1217",label:"广点通"},{id:"1218+1242",label:"渠道"},{id:"1243+1267",label:"移信"},{id:"1268+1292",label:"头条"}]},{label:"金立游戏",children:[{id:"1293+1296",label:"统计"},{id:"1297+1316",label:"广点通"},{id:"1317+1341",label:"渠道"},{id:"1342+1366",label:"移信"},{id:"1367+1391",label:"头条"}]},{label:"华为",children:[{id:"1392+1395",label:"统计"},{id:"1396+1415",label:"广点通"},{id:"1416+1440",label:"渠道"},{id:"1441+1465",label:"移信"},{id:"1466+1490",label:"头条"}]},{label:"中兴",children:[{id:"1491+1494",label:"统计"},{id:"1495+1514",label:"广点通"},{id:"1515+1539",label:"渠道"},{id:"1540+1564",label:"移信"},{id:"1565+1589",label:"头条"}]},{label:"taptap",children:[{id:"1590+1593",label:"统计"},{id:"1594+1613",label:"广点通"},{id:"1614+1638",label:"渠道"},{id:"1639+1663",label:"移信"},{id:"1664+1688",label:"头条"}]},{label:"好游快爆",children:[{id:"1689+1692",label:"统计"},{id:"1693+1712",label:"广点通"},{id:"1713+1737",label:"渠道"},{id:"1738+1762",label:"移信"},{id:"1763+1787",label:"头条"}]}],hackReset:!1,secondary_platform:"",secondary_date:"",secondary_channel:"",secondary_adtype:"",secondary_datelist:[],hidlist:[],options:[{value:"全部",label:"全部"},{value:"横幅",label:"横幅"},{value:"开屏",label:"开屏"},{value:"插屏",label:"插屏"},{value:"视频",label:"视频"}],pickerOptions0:{disabledDate:function(e){return e.getTime()>Date.now()-864e4}},directives:{waves:o.a},downloadLoading:!1,layout:"",chooseName:"选择游戏",chooseNamed:"选择游戏",aupu_chooseNamed:"选择项目",select_value:"",download_value:"",arpu_download_value:"",downloadParam:{start:void 0,end:void 0,name:void 0,namelist:"",list:[]},aupu_downloadParam:{start:void 0,end:void 0,name:void 0},searchName:"搜索",tableKey:0,list:null,total:0,listLoading:!0,listQuery:{importance:void 0,title:void 0,type:void 0},listParam:{start:void 0,end:void 0,name:void 0},importanceOptions:[1,2,3],names:[],sortOptions:[{label:"ID Ascending",key:"+id"},{label:"ID Descending",key:"-id"}],statusOptions:["published","draft","deleted"]}},created:function(){this.fetchName(),this.fetchProject()},methods:{arpu_handleDownloadAll:function(){var e=this,a=this;null!==this.aupu_downloadParam&&"选择项目"!==this.aupu_downloadParam.name&&void 0!==this.arpu_download_value[0]&&void 0!==this.arpu_download_value[1]?this.arpu_download_value[1]-this.arpu_download_value[0]>5184e5?a.$notify({title:"",message:"时间范围最多选择7天",type:"warning",duration:2e3}):(this.aupu_downloadParam.start=this.formatDate(new Date(this.arpu_download_value[0]),"yyyy-MM-dd"),this.aupu_downloadParam.end=this.formatDate(new Date(this.arpu_download_value[1]),"yyyy-MM-dd"),this.aupu_downloadParam.name=this.aupu_chooseNamed,this.downloadLoading=!0,function(e){return Object(i.a)({url:"/arpufile/"+e.start+"_"+e.end,method:"post",responseType:"blob",params:e,data:e})}(this.aupu_downloadParam).then(function(a){if(a){var t=window.URL.createObjectURL(new Blob([a])),l=document.createElement("a");l.style.display="none",l.href=t,l.setAttribute("download",e.aupu_downloadParam.start+"_"+e.aupu_downloadParam.end+e.aupu_downloadParam.name.project_name+"_arpu.xls"),document.body.appendChild(l),l.click(),e.downloadLoading=!1}}).catch(function(e){console.log(e.toString()),a.downloadLoading=!1,a.$notify({title:"下载失败",message:"刷新试试",type:"error",duration:2e3})})):this.open3()},daily_download:function(){var e=this,a=this;if(null!==this.daily_download_value)if(void 0!==this.daily_download_value[0])if(void 0!==this.daily_download_value[1]){this.downloadLoading=!0;var t={start:this.daily_download_value[0],end:this.daily_download_value[1],list:this.app_name_list};(function(e){return Object(i.a)({url:"/daily",method:"post",responseType:"blob",data:e})})(t).then(function(a){if(a){var l=window.URL.createObjectURL(new Blob([a])),n=document.createElement("a");n.style.display="none",n.href=l,n.setAttribute("download",t.start+"_"+t.end+"_产品收益表.xls"),document.body.appendChild(n),n.click(),e.downloadLoading=!1}}).catch(function(e){console.log(e.toString()),a.downloadLoading=!1,a.$notify({title:"下载失败",message:"刷新试试",type:"error",duration:2e3})})}else this.open3();else this.open3();else this.open3()},daily_adtype_download:function(){var e=this,a=this;if(null!==this.daily_adtype_download_value)if("选择项目"!==this.daily_adtype_chooseNamed.name)if(void 0!==this.daily_adtype_download_value[0])if(void 0!==this.daily_adtype_download_value[1]){this.downloadLoading=!0;var t={start:this.daily_adtype_download_value[0],end:this.daily_adtype_download_value[1],list:this.daily_adtype_chooseNamed};(function(e){return Object(i.a)({url:"/dailyadtype",method:"post",responseType:"blob",data:e})})(t).then(function(a){if(a){var l=window.URL.createObjectURL(new Blob([a])),n=document.createElement("a");n.style.display="none",n.href=l,n.setAttribute("download",t.start+"_"+t.end+t.list.project_name+"_arpu.xls"),document.body.appendChild(n),n.click(),e.downloadLoading=!1}}).catch(function(e){console.log(e.toString()),a.downloadLoading=!1,a.$notify({title:"下载失败",message:"刷新试试",type:"error",duration:2e3})})}else this.open3();else this.open3();else this.open3();else this.open3()},fetchProject:function(){var e=this,a=this;this.listLoading=!0,Object(i.a)({url:"/projectList",method:"get"}).then(function(a){e.app_name_list=a.data}).catch(function(e){console.log(e),a.listLoading=!1})},getnumlist:function(){var e=this;this.hackReset=!1,this.downloadLoading=!0;for(var a=this,t=this.$refs.tree.getCheckedKeys(),l=[],n=0;n<t.length;n++)if(void 0!==t[n]){var o=this.getNum(parseInt(t[n].split("+")[0]),parseInt(t[n].split("+")[1]));l=l.concat(o)}this.downloadParam.list=l,function(e){return Object(i.a)({url:"/file",method:"post",data:e,responseType:"blob"})}(this.downloadParam).then(function(a){if(a){var t=window.URL.createObjectURL(new Blob([a])),l=document.createElement("a");l.style.display="none",l.href=t,l.setAttribute("download",e.downloadParam.start+"_"+e.downloadParam.end+"_"+e.downloadParam.name.project_name+".xls"),document.body.appendChild(l),l.click(),e.downloadLoading=!1}}).catch(function(e){a.downloadLoading=!1,a.$notify({title:"下载失败",message:"刷新试试",type:"error",duration:2e3})})},getNum:function(e,a){var t=[];t.push(e);for(var l=e+1;l<=a;l++)t.push(l);return t},chooseyixin:function(){this.$refs.tree.setCheckedKeys(["80+104","179+203","278+302","377+401","476+500","575+599","674+698","773+797","872+896","971+995","1070+1094","1169+1193","1268+1292","1367+1391","55+79","154+178","253+277","352+376","451+475","550+574","649+673","748+772","847+871","946+970","1045+1069","1144+1168","1243+1267","1342+1366","1441+1465","1540+1564","1639+1663","1738+1762","921+945","1020+1044","1218+1242","1317+1341","1416+1440","1515+1539","1614+1638","1713+1737","897+900","1194+1197","1293+1296","1392+1395"])},rechoose:function(){this.$refs.tree.setCheckedKeys([])},fetchName:function(){var e=this;if(Object(s.a)(["admin","leader","operator"])){var a=this;this.listLoading=!0,Object(i.a)({url:"/names",method:"get"}).then(function(a){e.names=a.data,e.listLoading=!1}).catch(function(e){a.listLoading=!1,console.log(e)})}else{var t={username:this.name},l=this;this.listLoading=!0,function(e){return Object(i.a)({url:"/api/getresourcelist",method:"post",params:e})}(t).then(function(a){e.names=a.data,e.listLoading=!1}).catch(function(e){l.listLoading=!1,console.error(e)})}},handleFilter:function(){var e=this;this.downloadLoading=!0;var a=this;if(this.listParam.name=this.chooseName,"选择游戏"===this.listParam.name||""===this.select_value||null==this.select_value)return this.open3(),void(this.downloadLoading=!1);this.listParam.start=this.select_value[0],this.listParam.end=this.select_value[1],this.listLoading=!0,function(e){return Object(i.a)({url:"/data/"+e.start+"_"+e.end+"_"+e.name,method:"get"})}(this.listParam).then(function(a){console.log(),e.list=a.data,e.hidlist=a.data,e.listLoading=!1,e.downloadLoading=!1}).catch(function(e){a.listLoading=!1,a.downloadLoading=!1})},getDatawithName:function(){if(this.hidlist!==[]){var e=[];this.listLoading=!0;var a=this.secondary_adtype;if(""==a||null==a)e=this.hidlist;else for(var t=0;t<this.hidlist.length;t++)-1!=this.hidlist[t].advertising_type.search(a)&&e.push(this.hidlist[t]);var l=[],n=this.secondary_channel;if(""==n)l=e;else for(var i=0;i<e.length;i++)-1!=e[i].channel.search(n)&&l.push(e[i]);var o=[],d=this.secondary_date;if(""==d||"全部"==d)o=l;else for(var s=0;s<l.length;s++)-1!=l[s].date.search(d)&&o.push(l[s]);var r=[],c=this.secondary_platform;if(""==c||null==c)r=o;else for(var u=0;u<o.length;u++)-1!=o[u].platform.search(c)&&r.push(o[u]);this.list=r,this.listLoading=!1}},handleDownloadAll:function(){null!==this.download_value?(this.downloadParam.start=this.download_value[0],this.downloadParam.end=this.download_value[1],this.downloadParam.name=this.secondary_project,"选择项目"!==this.downloadParam.name&&void 0!==this.downloadParam.start&&void 0!==this.downloadParam.end?this.hackReset=!0:this.open3()):this.open3()},handleDownload:function(){var e=this;if(this.downloadLoading=!0,null===this.list)return this.open4(),void(this.downloadLoading=!1);Promise.all([t.e("chunk-0d49"),t.e("chunk-929e")]).then(t.bind(null,"S/jZ")).then(function(a){var t=e.formatJson(["id","date","app_name","channel","advertising_type","earned","click_rate","ecpm","impression","click","fill_rate","platform"],e.list);a.export_json_to_excel({header:["id","date","app_name","channel","advertising_type","earned","click_rate","ecpm","impression","click","fill_rate","platform"],data:t,filename:"table-list"}),e.downloadLoading=!1})},secondart_date_change:function(){null!==this.select_value&&(this.secondary_datelist=this.getdate(new Date(this.select_value[0]),new Date(this.select_value[1])))},formatJson:function(e,a){return a.map(function(a){return e.map(function(e){return a[e]})})},checkPermission:s.a,parseTime:d.c,getdate:function(e,a){var t=[];for(t.push("全部"),t.push(this.formatDate(e,"yyyy-MM-dd"));e.setDate(e.getDate()+1),e.getTime()<a.getTime();)t.push(this.formatDate(e,"yyyy-MM-dd"));return t.includes(this.formatDate(a,"yyyy-MM-dd"))||t.push(this.formatDate(a,"yyyy-MM-dd")),t},formatDate:function(e,a){/(y+)/.test(a)&&(a=a.replace(RegExp.$1,(e.getFullYear()+"").substr(4-RegExp.$1.length)));var t={"M+":e.getMonth()+1,"d+":e.getDate(),"h+":e.getHours(),"m+":e.getMinutes(),"s+":e.getSeconds()};for(var l in t)if(new RegExp("("+l+")").test(a)){var n=t[l]+"";a=a.replace(RegExp.$1,1===RegExp.$1.length?n:this.padLeftZero(n))}return a},padLeftZero:function(e){return("00"+e).substr(e.length)},open3:function(){this.$message({message:"请选择查询范围~",type:"warning"})},open4:function(){this.$message({message:"没有信息可以打印~",type:"warning"})}}},u=t("KHd+"),h=Object(u.a)(c,function(){var e=this,a=e.$createElement,t=e._self._c||a;return t("div",{staticClass:"app-container"},[t("div",{staticClass:"filter-container",staticStyle:{margin:"15px","margin-top":"-5px"}},[t("el-date-picker",{attrs:{type:"daterange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期","value-format":"yyyy-MM-dd","picker-options":e.pickerOptions0},on:{change:e.secondart_date_change},model:{value:e.select_value,callback:function(a){e.select_value=a},expression:"select_value"}}),e._v(" "),t("el-select",{staticClass:"filter-item",staticStyle:{width:"140px"},attrs:{placeholder:"选择游戏",filterable:""},model:{value:e.chooseName,callback:function(a){e.chooseName=a},expression:"chooseName"}},e._l(e.names,function(e){return t("el-option",{key:e,attrs:{label:e,value:e}})})),e._v(" "),t("el-button",{directives:[{name:"waves",rawName:"v-waves"}],staticClass:"filter-item",attrs:{loading:e.downloadLoading,type:"primary",icon:"el-icon-search"},on:{click:e.handleFilter}},[e._v(e._s(e.searchName)+"\n    ")]),e._v(" "),t("el-button",{directives:[{name:"waves",rawName:"v-waves"}],attrs:{loading:e.downloadLoading,type:"primary",icon:"el-icon-download"},on:{click:e.handleDownload}},[e._v("\n      "+e._s("下载")+"\n    ")]),e._v(" "),t("br")],1),e._v(" "),t("div",{staticStyle:{"margin-top":"-10px"}},[t("el-input",{staticClass:"filter-item",staticStyle:{width:"200px","margin-left":"15px","margin-bottom":"10px","margin-top":"-10px"},attrs:{placeholder:"根据渠道二次筛选",clearable:""},on:{blur:e.getDatawithName},model:{value:e.secondary_channel,callback:function(a){e.secondary_channel=a},expression:"secondary_channel"}}),e._v(" "),t("el-select",{attrs:{placeholder:"根据广告类型二次筛选"},on:{change:e.getDatawithName},model:{value:e.secondary_adtype,callback:function(a){e.secondary_adtype=a},expression:"secondary_adtype"}},e._l(e.options,function(e){return t("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),e._v(" "),t("el-select",{staticStyle:{width:"170px"},attrs:{placeholder:"根据时间二次筛选"},on:{change:e.getDatawithName},model:{value:e.secondary_date,callback:function(a){e.secondary_date=a},expression:"secondary_date"}},e._l(e.secondary_datelist,function(e){return t("el-option",{key:e,attrs:{label:e,value:e}})})),e._v(" "),t("el-input",{staticClass:"filter-item",staticStyle:{width:"200px","margin-bottom":"10px","margin-top":"-10px"},attrs:{placeholder:"根据平台二次筛选",clearable:""},on:{blur:e.getDatawithName},model:{value:e.secondary_platform,callback:function(a){e.secondary_platform=a},expression:"secondary_platform"}})],1),e._v(" "),t("el-dialog",{attrs:{title:"选择需要去除的部分",visible:e.hackReset,width:"40%"},on:{"update:visible":function(a){e.hackReset=a}}},[t("el-button",{on:{click:function(a){e.chooseyixin()}}},[e._v("模板一")]),e._v(" "),t("el-button",{on:{click:function(a){e.rechoose()}}},[e._v("还原")]),e._v(" "),t("el-tree",{ref:"tree",attrs:{data:e.data2,"node-key":"id","show-checkbox":"","highlight-current":""}}),e._v(" "),t("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(a){e.hackReset=!1}}},[e._v(e._s("取消"))]),e._v(" "),t("el-button",{attrs:{type:"primary"},on:{click:e.getnumlist}},[e._v(e._s("确认"))])],1)],1),e._v(" "),t("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],attrs:{height:"850",stripe:"",data:e.list,"element-loading-text":"Loading",border:"",fit:"",fixed:"","highlight-current-row":""}},[t("el-table-column",{attrs:{align:"center",label:"ID",width:"95"},scopedSlots:e._u([{key:"default",fn:function(a){return[e._v("\n        "+e._s(++a.$index)+"\n      ")]}}])}),e._v(" "),t("el-table-column",{attrs:{label:"时间",width:"110",align:"center",prop:"date"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("span",[e._v(e._s(a.row.date))])]}}])}),e._v(" "),t("el-table-column",{attrs:{label:"游戏名",width:"110",align:"center",prop:"app_name"},scopedSlots:e._u([{key:"default",fn:function(a){return[e._v("\n        "+e._s(a.row.app_name)+"\n      ")]}}])}),e._v(" "),t("el-table-column",{attrs:{align:"center",label:"渠道",prop:"channel"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("span",[e._v(e._s(a.row.channel))])]}}])}),e._v(" "),t("el-table-column",{attrs:{align:"center",label:"广告类型",prop:"advertising_type"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("span",[e._v(e._s(a.row.advertising_type))])]}}])}),e._v(" "),t("el-table-column",{attrs:{align:"center",label:"收入",prop:"earned"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("span",[e._v(e._s(a.row.earned))])]}}])}),e._v(" "),t("el-table-column",{attrs:{align:"center",label:"点击率",prop:"clickrate"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("span",[e._v(e._s(a.row.click_rate))])]}}])}),e._v(" "),t("el-table-column",{attrs:{align:"center",label:"ecpm",prop:"ecpm"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("span",[e._v(e._s(a.row.ecpm))])]}}])}),e._v(" "),t("el-table-column",{attrs:{align:"center",label:"展示次数",prop:"impression"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("span",[e._v(e._s(a.row.impression))])]}}])}),e._v(" "),t("el-table-column",{attrs:{align:"center",label:"点击次数",prop:"click"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("span",[e._v(e._s(a.row.click))])]}}])}),e._v(" "),t("el-table-column",{attrs:{align:"center",label:"填充率",prop:"fill_rate"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("span",[e._v(e._s(a.row.fill_rate))])]}}])}),e._v(" "),t("el-table-column",{attrs:{align:"center",label:"备注",prop:"platform",width:"200"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("span",[e._v(e._s(a.row.note))])]}}])}),e._v(" "),t("el-table-column",{attrs:{align:"center",label:"平台",prop:"platform"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("span",[e._v(e._s(a.row.platform))])]}}])})],1)],1)},[],!1,null,null,null);h.options.__file="addata.vue";a.default=h.exports},ZySA:function(e,a,t){"use strict";var l=t("P2sY"),n=t.n(l),i=(t("jUE0"),{bind:function(e,a){e.addEventListener("click",function(t){var l=n()({},a.value),i=n()({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},l),o=i.ele;if(o){o.style.position="relative",o.style.overflow="hidden";var d=o.getBoundingClientRect(),s=o.querySelector(".waves-ripple");switch(s?s.className="waves-ripple":((s=document.createElement("span")).className="waves-ripple",s.style.height=s.style.width=Math.max(d.width,d.height)+"px",o.appendChild(s)),i.type){case"center":s.style.top=d.height/2-s.offsetHeight/2+"px",s.style.left=d.width/2-s.offsetWidth/2+"px";break;default:s.style.top=(t.pageY-d.top-s.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",s.style.left=(t.pageX-d.left-s.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return s.style.backgroundColor=i.color,s.className="waves-ripple z-active",!1}},!1)}}),o=function(e){e.directive("waves",i)};window.Vue&&(window.waves=i,Vue.use(o)),i.install=o;a.a=i},jUE0:function(e,a,t){}}]);
(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-58a1"],{"3Gho":function(e,t,a){"use strict";a.d(t,"b",function(){return l}),a.d(t,"f",function(){return i}),a.d(t,"d",function(){return o}),a.d(t,"c",function(){return s}),a.d(t,"a",function(){return r}),a.d(t,"e",function(){return c});var n=a("t3Un");function l(){return Object(n.a)({url:"/names",method:"get"})}function i(e){return Object(n.a)({url:"/file",method:"post",data:e,responseType:"blob"})}function o(e){return Object(n.a)({url:"/api/getresourcelist",method:"post",params:e})}function s(){return Object(n.a)({url:"/projectList",method:"get"})}function r(e){return Object(n.a)({url:"/daily",method:"post",responseType:"blob",data:e})}function c(e){return Object(n.a)({url:"/arpufile_preview/"+e.start+"_"+e.end,method:"post",params:e,data:e})}},"41Be":function(e,t,a){"use strict";a.d(t,"a",function(){return l});var n=a("Q2AE");function l(e){if(e&&e instanceof Array&&e.length>0){var t=e;return!!(n.a.getters&&n.a.getters.roles).some(function(e){return t.includes(e)})}return!1}},BvFL:function(e,t,a){"use strict";a.d(t,"a",function(){return l});var n=a("t3Un");function l(e){return Object(n.a)({url:"/api/getresourcelist",method:"post",params:e})}},HAgL:function(e,t,a){"use strict";var n=a("yjTf");a.n(n).a},K15r:function(e,t,a){"use strict";a.r(t);var n=a("3Gho"),l=a("41Be"),i=a("BvFL"),o=a("Q2AE"),s=a("t3Un");function r(e){return Object(s.a)({url:"/umengretention",method:"post",data:e})}var c=a("ZySA"),u=(a("7Qib"),a("nQns")),d={directives:{waves:c.a},filters:{statusFilter:function(e){return{published:"success",draft:"gray",deleted:"danger"}[e]}},data:function(){return{timevalueLtv:[],activeName:"first",arpuList:[],daily_download_value:[],app_name_list:[],channel_value:"",channelList:[],average:{one:0,three:0,seven:0,fourteen:0,thirty:0,lt:"0"},fullscreenLoading:!1,options:"",select_value:"",pickerOptions0:{disabledDate:function(e){return e.getTime()>Date.now()-864e4-1728e5}},directives:{waves:c.a},downloadLoading:!1,layout:"",timevalue:[],searchName:"搜索",tableKey:0,list:[],total:0,listLoading:!1,listQuery:{importance:void 0,title:void 0,type:void 0},listParam:{start:"",end:"",name:"",appkey:"",channel:""},importanceOptions:[1,2,3],names:[],sortOptions:[{label:"ID Ascending",key:"+id"},{label:"ID Descending",key:"-id"}],statusOptions:["published","draft","deleted"]}},mounted:function(){this.initapps(),this.fetchProject()},methods:{test:function(){for(var e=this.formatDate(new Date(this.timevalueLtv[0]),"yyyy-MM-dd"),t=this.formatDate(new Date(this.timevalueLtv[1]),"yyyy-MM-dd"),a=this.arpuList,n=this.options,l=[],i=1;i<3;i++){for(var o=a[i][0],s="",r=!1,c=0;c<n.length;c++)if(-1!=n[c].name.indexOf(o)){s=n[c].appkey,r=!0;break}console.log(o),console.log(r),r&&l.push(this.handleFilter1(e,t,o,s,"全部"))}},initdate:function(){var e=new Date,t=new Date;t.setTime(t.getTime()-6912e5),e.setTime(e.getTime()-1728e5),this.daily_download_value.push(t),this.daily_download_value.push(e),this.timevalueLtv.push(t),this.timevalueLtv.push(e),this.daily_download1()},fetchProject:function(){var e=this,t=this;Object(n.c)().then(function(t){e.app_name_list=t.data,e.initdate()}).catch(function(e){console.log(e),t.tabLoading=!1})},daily_download1:function(){var e=this,t=this;if(null!==this.daily_download_value)if(void 0!==this.daily_download_value[0])if(void 0!==this.daily_download_value[1]){this.downloadLoading=!0;var a={start:this.daily_download_value[0],end:this.daily_download_value[1],list:this.app_name_list};this.arpuList=[],Object(u.a)(a).then(function(a){if(a){e.original=a.data.original;for(var n=a.data.arpu,l=0;l<n[0].length;l++){for(var i=[],o=0;o<n.length;o++)i.push(n[o][l]);e.arpuList.push(i)}for(var s=e.arpuList,r=0;r<s.length;r++);e.downloadLoading=!1,t.tabLoading=!1}}).catch(function(e){console.error(e.toString()+""),t.downloadLoading=!1,t.tabLoading=!1,t.$notify({title:"获取失败",message:"刷新试试",type:"error",duration:2e3})})}else this.open3();else this.open3();else this.open3()},checkPermission:l.a,changeselect:function(){var e=this,t=this;(function(e){return Object(s.a)({url:"/umengchannels/"+e,method:"get"})})(this.select_value.appkey).then(function(t){e.channelList=t.data.data;e.channelList=[{id:"全部",channel:"全部"}].concat(e.channelList),e.channel_value="全部"}).catch(function(e){t.$notify({title:"初始化app列表",message:"刷新试试",type:"error",duration:2e3})})},initapps:function(){var e=this,t=this;t.fullscreenLoading=!0,Object(s.a)({url:"/umengapp",method:"get"}).then(function(a){var n=a.data.data;if(e.checkPermission(["director"])||e.checkPermission(["admin"])||e.checkPermission(["operatorleader"])||e.checkPermission(["sdksuport"]))e.options=n;else{var l=[],s={username:o.a.getters&&o.a.getters.name};Object(i.a)(s).then(function(e){for(var t=e.data,a=0;a<n.length;a++){for(var i=!1,o=0;o<t.length;o++)-1!=n[a].name.indexOf(t[o])&&(i=!0);i&&l.push(n[a])}}),e.options=l}t.fullscreenLoading=!1}).catch(function(e){t.$notify({title:"初始化app列表",message:"刷新试试",type:"error",duration:2e3}),t.fullscreenLoading=!1})},handleFilter:function(){var e=this;this.list=[];var t=this;if(this.downloadLoading=!0,""===this.timevalue||null==this.timevalue||"undefined"===this.timevalue[0]||"undefined"===this.timevalue[1]||""===this.select_value)return this.$message({message:"记得选择查询范围~",type:"warning"}),void(this.downloadLoading=!1);var a=new Date(this.timevalue[0]).getTime();if(new Date(this.timevalue[1]).getTime()-a>2592e6)return t.$notify({title:"",message:"时间范围最多选择30天",type:"warning",duration:2e3}),void(this.downloadLoading=!1);console.log(this.select_value.name),console.log(this.select_value.appkey),console.log(this.channel_value),this.listParam.start=this.timevalue[0],this.listParam.end=this.timevalue[1],this.listParam.name=this.select_value.name,this.listParam.appkey=this.select_value.appkey,this.listParam.channel=this.channel_value,this.listLoading=!0,r(this.listParam).then(function(t){console.log(t.data.data);for(var a=t.data.data,n=0,l=0,i=0,o=0,s=0,r=0;r<a.length;r++){var c=a[r].install_period,u=a[r].retention_rate,d=void 0===u[0]?0:u[0],v=void 0===u[1]?0:u[1],p=void 0===u[2]?0:u[2],h=void 0===u[3]?0:u[3],f=void 0===u[4]?0:u[4],g=void 0===u[5]?0:u[5],m=void 0===u[6]?0:u[6],_=void 0===u[7]?0:u[7],y=void 0===u[8]?0:u[8],b={date:c,oneDay:d,twoDay:v,threeDay:p,fourDay:h,fiveDay:f,sixDay:g,sevenDay:m,halfmonth:_,onemonth:y,lt:((1.5*(p+m)+3*(m+_)+7.5*(_+y)+d+p+m+_+y)/d*d/100+1).toFixed(4)};n+=d,l+=p,i+=m,o+=_,s+=y,e.list.push(b)}var w=n/a.length,k=l/a.length,L=i/a.length,x=o/a.length,D=s/a.length;e.average={one:(n/a.length).toFixed(2),three:(l/a.length).toFixed(2),seven:(i/a.length).toFixed(2),fourteen:(o/a.length).toFixed(2),thirty:(s/a.length).toFixed(2),lt:((1.5*(k+L)+3*(L+x)+7.5*(x+D)+w+k+L+x+D)/w*w/100+1).toFixed(4)},e.listLoading=!1,e.downloadLoading=!1}).catch(function(e){console.error(e),t.listLoading=!1,t.downloadLoading=!1})},handleFilter1:function(e,t,a,n,l){r({start:e,end:t,name:a,appkey:n,channel:l}).then(function(e){for(var t=e.data.data,a=[],n=0;n<t.length;n++){var l=t[n].install_period,i=t[n].retention_rate,o=void 0===i[0]?0:i[0],s=(void 0===i[1]||i[1],void 0===i[2]?0:i[2]),r=(void 0===i[3]||i[3],void 0===i[4]||i[4],void 0===i[5]||i[5],void 0===i[6]?0:i[6]),c=void 0===i[7]?0:i[7],u=void 0===i[8]?0:i[8],d={date:l,lt:((1.5*(s+r)+3*(r+c)+7.5*(c+u)+o+s+r+c+u)/o*o/100+1).toFixed(4)};a.push(d)}return console.log(a),a}).catch(function(e){return[]})},setretention:function(e,t){for(var a=0;a<t.length;a++){var n=t[a].channelName,l=t[a].list;if(e.channel===n)for(var i=0;i<l.length;i++)if(l[i].install_period===e.date)return e.total_install=l[i].retention_rate[0],e.total_install_rate=0,e}return e.total_install=0,e.total_install_rate=0,e},handleDownload:function(){var e=this;if(this.downloadLoading=!0,null===this.list)return this.$message({message:"没有信息可以打印~",type:"warning"}),void(this.downloadLoading=!1);Promise.all([a.e("chunk-0d49"),a.e("chunk-9284")]).then(a.bind(null,"S/jZ")).then(function(t){var a=e.formatJson(["date","id","channel","install","active_user","launch","duration","total_install","total_install_rate"],e.list);t.export_json_to_excel({header:["时间","应用名","渠道","新增人数","活跃人数","启动次数","单次启动时长","次日留存率","版本"],data:a,filename:"友盟3"}),e.downloadLoading=!1})},formatJson:function(e,t){return t.map(function(t){return e.map(function(e){return t[e]})})},formatDate:function(e,t){/(y+)/.test(t)&&(t=t.replace(RegExp.$1,(e.getFullYear()+"").substr(4-RegExp.$1.length)));var a={"M+":e.getMonth()+1,"d+":e.getDate(),"h+":e.getHours(),"m+":e.getMinutes(),"s+":e.getSeconds()};for(var n in a)if(new RegExp("("+n+")").test(t)){var l=a[n]+"";t=t.replace(RegExp.$1,1===RegExp.$1.length?l:this.padLeftZero(l))}return t},padLeftZero:function(e){return("00"+e).substr(e.length)}}},v=(a("HAgL"),a("KHd+")),p=Object(v.a)(d,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-tabs",{on:{"tab-click":function(e){}},model:{value:e.activeName,callback:function(t){e.activeName=t},expression:"activeName"}},[a("el-tab-pane",{attrs:{label:"lt",name:"first"}},[a("div",{staticClass:"filter-container",staticStyle:{margin:"15px","margin-top":"15px"}},[a("el-date-picker",{attrs:{type:"daterange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期","value-format":"yyyy-MM-dd","picker-options":e.pickerOptions0},model:{value:e.timevalue,callback:function(t){e.timevalue=t},expression:"timevalue"}},[e._v(">\n        ")]),e._v(" "),a("el-select",{attrs:{filterable:"",placeholder:"请选择应用","value-key":"name"},on:{change:e.changeselect},model:{value:e.select_value,callback:function(t){e.select_value=t},expression:"select_value"}},e._l(e.options,function(t){return a("el-option",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.fullscreenLoading,expression:"fullscreenLoading",modifiers:{fullscreen:!0,lock:!0}}],key:t.name,attrs:{label:t.name,value:t}})})),e._v(" "),a("el-select",{attrs:{filterable:"",placeholder:"请选择渠道","value-key":"id"},model:{value:e.channel_value,callback:function(t){e.channel_value=t},expression:"channel_value"}},e._l(e.channelList,function(t){return a("el-option",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.fullscreenLoading,expression:"fullscreenLoading",modifiers:{fullscreen:!0,lock:!0}}],key:t.id,attrs:{label:t.channel,value:t.id}})})),e._v(" "),a("el-button",{staticClass:"filter-item",attrs:{loading:e.downloadLoading,type:"primary",icon:"el-icon-search"},on:{click:e.handleFilter}},[e._v(e._s(e.searchName)+"\n        ")]),e._v(" "),a("el-button",{directives:[{name:"waves",rawName:"v-waves"}],attrs:{loading:e.downloadLoading,type:"primary",icon:"el-icon-download"},on:{click:e.handleDownload}},[e._v("\n          "+e._s("下载")+"\n        ")])],1),e._v(" "),a("el-card",{staticStyle:{"margin-bottom":"10px"},attrs:{shadow:"always"}},[a("el-tag",[e._v("统计数据(平均值)")]),e._v(" "),a("el-tag",{attrs:{type:"success"}},[e._v("次日留存："+e._s(e.average.one))]),e._v(" "),a("el-tag",{attrs:{type:"success"}},[e._v("三日留存："+e._s(e.average.three))]),e._v(" "),a("el-tag",{attrs:{type:"success"}},[e._v("七日留存："+e._s(e.average.seven))]),e._v(" "),a("el-tag",{attrs:{type:"success"}},[e._v("十四日留存："+e._s(e.average.fourteen))]),e._v(" "),a("el-tag",{attrs:{type:"success"}},[e._v("三十日留存："+e._s(e.average.thirty))]),e._v(" "),a("el-tag",{attrs:{type:"success"}},[e._v("生命周期："+e._s(e.average.lt))])],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],attrs:{height:"700",data:e.list,"element-loading-text":"Loading",border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{align:"center",label:"ID",width:"95"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n            "+e._s(++t.$index)+"\n          ")]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"时间",width:"110",align:"center",prop:"date"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.date))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"次日留存",prop:"id"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.oneDay))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"二日留存",prop:"id"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.twoDay))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"三日留存",prop:"id"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.threeDay))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"四日留存",prop:"id"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.fourDay))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"五日留存",prop:"id"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.fiveDay))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"六日留存",prop:"id"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.sixDay))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"七日留存",prop:"id"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.sevenDay))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"十四日留存",prop:"id"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.halfmonth))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"三十日留存",prop:"id"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.onemonth))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"生命周期(lt)",prop:"id"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.lt))])]}}])})],1)],1),e._v(" "),a("el-tab-pane",{attrs:{label:"ltv",name:"second"}},[a("div",{staticClass:"filter-container",staticStyle:{margin:"15px","margin-top":"15px"}},[a("el-date-picker",{attrs:{type:"daterange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期","picker-options":e.pickerOptions0},model:{value:e.timevalueLtv,callback:function(t){e.timevalueLtv=t},expression:"timevalueLtv"}},[e._v(">\n        ")]),e._v(" "),a("el-select",{attrs:{filterable:"",placeholder:"请选择应用","value-key":"name"},on:{change:e.changeselect},model:{value:e.select_value,callback:function(t){e.select_value=t},expression:"select_value"}},e._l(e.options,function(t){return a("el-option",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.fullscreenLoading,expression:"fullscreenLoading",modifiers:{fullscreen:!0,lock:!0}}],key:t.name,attrs:{label:t.name,value:t}})})),e._v(" "),a("el-select",{attrs:{filterable:"",placeholder:"请选择渠道","value-key":"id"},model:{value:e.channel_value,callback:function(t){e.channel_value=t},expression:"channel_value"}},e._l(e.channelList,function(t){return a("el-option",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.fullscreenLoading,expression:"fullscreenLoading",modifiers:{fullscreen:!0,lock:!0}}],key:t.id,attrs:{label:t.channel,value:t.id}})})),e._v(" "),a("el-button",{staticClass:"filter-item",attrs:{loading:e.downloadLoading,type:"primary",icon:"el-icon-search"},on:{click:e.handleFilter1}},[e._v(e._s(e.searchName)+"\n        ")]),e._v(" "),a("el-button",{directives:[{name:"waves",rawName:"v-waves"}],attrs:{loading:e.downloadLoading,type:"primary",icon:"el-icon-download"},on:{click:e.test}},[e._v("\n          "+e._s("下载")+"\n        ")])],1),e._v(" "),a("table",{staticClass:"gridtable"},e._l(e.arpuList,function(t,n){return a("tr",e._l(t,function(t,l){return a("td",[0!==n&&0==l?a("el-button",{on:{click:function(e){}}},[e._v(e._s(t))]):e._e(),e._v(" "),0===n||0!=l?a("span",[e._v(e._s(t))]):e._e()],1)}))}))])],1)],1)},[],!1,null,null,null);p.options.__file="ltv.vue";t.default=p.exports},ZySA:function(e,t,a){"use strict";var n=a("P2sY"),l=a.n(n),i=(a("jUE0"),{bind:function(e,t){e.addEventListener("click",function(a){var n=l()({},t.value),i=l()({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},n),o=i.ele;if(o){o.style.position="relative",o.style.overflow="hidden";var s=o.getBoundingClientRect(),r=o.querySelector(".waves-ripple");switch(r?r.className="waves-ripple":((r=document.createElement("span")).className="waves-ripple",r.style.height=r.style.width=Math.max(s.width,s.height)+"px",o.appendChild(r)),i.type){case"center":r.style.top=s.height/2-r.offsetHeight/2+"px",r.style.left=s.width/2-r.offsetWidth/2+"px";break;default:r.style.top=(a.pageY-s.top-r.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",r.style.left=(a.pageX-s.left-r.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return r.style.backgroundColor=i.color,r.className="waves-ripple z-active",!1}},!1)}}),o=function(e){e.directive("waves",i)};window.Vue&&(window.waves=i,Vue.use(o)),i.install=o;t.a=i},jUE0:function(e,t,a){},nQns:function(e,t,a){"use strict";a.d(t,"a",function(){return l});var n=a("t3Un");function l(e){return Object(n.a)({url:"/daily_preview",method:"post",data:e})}},yjTf:function(e,t,a){}}]);
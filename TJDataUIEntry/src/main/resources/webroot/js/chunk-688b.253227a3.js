(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-688b"],{"0YTH":function(e,t,a){"use strict";a.d(t,"b",function(){return l}),a.d(t,"a",function(){return i}),a.d(t,"c",function(){return o});var n=a("t3Un");function l(){return Object(n.a)({url:"/umengapp",method:"get"})}function i(e){return Object(n.a)({url:"/umengretention",method:"post",data:e})}function o(e){return Object(n.a)({url:"/umengchannels/"+e,method:"get"})}},"41Be":function(e,t,a){"use strict";a.d(t,"a",function(){return l});var n=a("Q2AE");function l(e){if(e&&e instanceof Array&&e.length>0){var t=e;return!!(n.a.getters&&n.a.getters.roles).some(function(e){return t.includes(e)})}return!1}},BvFL:function(e,t,a){"use strict";a.d(t,"a",function(){return l});var n=a("t3Un");function l(e){return Object(n.a)({url:"/api/getresourcelist",method:"post",params:e})}},DTel:function(e,t,a){"use strict";var n=a("ceU9");a.n(n).a},ZySA:function(e,t,a){"use strict";var n=a("P2sY"),l=a.n(n),i=(a("jUE0"),{bind:function(e,t){e.addEventListener("click",function(a){var n=l()({},t.value),i=l()({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},n),o=i.ele;if(o){o.style.position="relative",o.style.overflow="hidden";var s=o.getBoundingClientRect(),r=o.querySelector(".waves-ripple");switch(r?r.className="waves-ripple":((r=document.createElement("span")).className="waves-ripple",r.style.height=r.style.width=Math.max(s.width,s.height)+"px",o.appendChild(r)),i.type){case"center":r.style.top=s.height/2-r.offsetHeight/2+"px",r.style.left=s.width/2-r.offsetWidth/2+"px";break;default:r.style.top=(a.pageY-s.top-r.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",r.style.left=(a.pageX-s.left-r.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return r.style.backgroundColor=i.color,r.className="waves-ripple z-active",!1}},!1)}}),o=function(e){e.directive("waves",i)};window.Vue&&(window.waves=i,Vue.use(o)),i.install=o;t.a=i},ceU9:function(e,t,a){},jUE0:function(e,t,a){},mdul:function(e,t,a){"use strict";a.r(t);var n=a("41Be"),l=a("BvFL"),i=a("Q2AE"),o=a("0YTH"),s=a("ZySA"),r=(a("7Qib"),{directives:{waves:s.a},filters:{statusFilter:function(e){return{published:"success",draft:"gray",deleted:"danger"}[e]}},data:function(){return{ltList1:[],timevalue1:"",channel_value1:[],select_value1:"",activeName:"first",ltList:[],channel_value:"",channelList:[],options:"",select_value:[],pickerOptions0:{disabledDate:function(e){return e.getTime()>Date.now()-864e4}},directives:{waves:s.a},downloadLoading:!1,layout:"",timevalue:"",searchName:"搜索",tableKey:0,list:[],total:0,listQuery:{importance:void 0,title:void 0,type:void 0},listParam:{start:"",end:"",name:"",appkey:"",channel:""},importanceOptions:[1,2,3],names:[],sortOptions:[{label:"ID Ascending",key:"+id"},{label:"ID Descending",key:"-id"}],statusOptions:["published","draft","deleted"]}},mounted:function(){this.initapps()},methods:{handleDownload:function(){var e=this;if(this.downloadLoading=!0,null===this.list)return this.$message({message:"没有信息可以打印~",type:"warning"}),void(this.downloadLoading=!1);for(var t=this.ltList1,n=this.ltList1[0],l=[],i=1;i<t.length;i++){for(var o={},s=0;s<t[i].length;s++){o[n[s]]=t[i][s]}l.push(o)}console.log(l),Promise.all([a.e("chunk-0d49"),a.e("chunk-9281")]).then(a.bind(null,"S/jZ")).then(function(t){var a=n,i=n,o=e.formatJson(i,l);t.export_json_to_excel({header:a,data:o,filename:"友盟留存"}),e.downloadLoading=!1})},handleDownload1:function(){var e=this;if(this.downloadLoading=!0,null===this.list)return this.$message({message:"没有信息可以打印~",type:"warning"}),void(this.downloadLoading=!1);for(var t=this.ltList,n=this.ltList[0],l=[],i=1;i<t.length;i++){for(var o={},s=0;s<t[i].length;s++){o[n[s]]=t[i][s]}l.push(o)}console.log(l),Promise.all([a.e("chunk-0d49"),a.e("chunk-9281")]).then(a.bind(null,"S/jZ")).then(function(t){var a=n,i=n,o=e.formatJson(i,l);t.export_json_to_excel({header:a,data:o,filename:"友盟留存"}),e.downloadLoading=!1})},checkPermission:n.a,initapps:function(){var e=this,t=this;Object(o.b)().then(function(t){var a=t.data.data,n=e.checkPermission(["director"])||e.checkPermission(["admin"])||e.checkPermission(["operatorleader"])||e.checkPermission(["sdksuport"]);if(console.log(n),n)console.log(a),e.options=a;else{var o=[],s={username:i.a.getters&&i.a.getters.name};Object(l.a)(s).then(function(e){for(var t=e.data,n=0;n<a.length;n++){for(var l=!1,i=0;i<t.length;i++)-1!=a[n].name.indexOf(t[i])&&(l=!0);l&&o.push(a[n])}}),e.options=o}}).catch(function(e){t.$notify({title:"初始化app列表",message:"刷新试试",type:"error",duration:2e3})})},test2:function(){if(!(this.select_value.length<1||void 0===this.timevalue||null===this.timevalue||""===this.timevalue)){this.downloadLoading=!0,this.ltList=[];for(var e=this.formatDate(new Date(this.timevalue[0]),"yyyy-MM-dd"),t=this.formatDate(new Date(this.timevalue[1]),"yyyy-MM-dd"),a="",n=this.select_value,l=0;l<n.length;l++){a=n[l].appkey;var i=n[l].name;this.handleFilter2(e,t,i,a,"全部",l,n.length-1)}}},test3:function(){if(!(""===this.select_value1||void 0===this.timevalue1||null===this.timevalue1||""===this.timevalue1||this.channelList.length<1)){this.downloadLoading=!0,this.ltList1=[];var e=this.formatDate(new Date(this.timevalue1[0]),"yyyy-MM-dd"),t=this.formatDate(new Date(this.timevalue1[1]),"yyyy-MM-dd"),a=this.select_value1.appkey,n="全部",l=[];l=this.channel_value1.length>0?this.channel_value1:this.channelList;for(var i=this.select_value1.name,o=0;o<l.length;o++)n=l[o].id,this.handleFilter3(e,t,i,a,n,o,l.length-1,l[o].channel)}},handleFilter2:function(e,t,a,n,l,i,s){var r=this,c={start:e,end:t,name:a,appkey:n,channel:l};console.log("method start"),Object(o.a)(c).then(function(e){var t=e.data.data,n=[];console.log("method success");for(var l=0;l<t.length;l++){var o=t[l].install_period,c=t[l].retention_rate,d=void 0===c[0]?0:c[0],u=(void 0===c[1]||c[1],void 0===c[2]?0:c[2]),h=(void 0===c[3]||c[3],void 0===c[4]||c[4],void 0===c[5]||c[5],void 0===c[6]?0:c[6]),v=void 0===c[7]?0:c[7],p=void 0===c[8]?0:c[8],f=((1.5*(u+h)+3*(h+v)+7.5*(v+p)+d+u+h+v+p)/d*d/100+1).toFixed(4),m={date:o,lt:window.isNaN(f)?0:f};n.push(m)}console.log("valuation start");var g=[];if(g.push(a),0===i){var y=[];y.push("日期");for(var _=0;_<n.length;_++)0===i&&y.push(n[_].date);r.ltList.splice(0,0,y)}for(var w=0;w<n.length;w++)g.push(parseFloat(n[w].lt).toFixed(4));r.ltList.push(g),console.log(r.ltList.length),i===s&&(r.downloadLoading=!1)}).catch(function(e){console.error(e)})},handleFilter3:function(e,t,a,n,l,i,s,r){var c=this,d={start:e,end:t,name:a,appkey:n,channel:l};console.log("method start"),Object(o.a)(d).then(function(e){var t=e.data.data,a=[];console.log("method success");for(var n=0;n<t.length;n++){var l=t[n].install_period,o=t[n].retention_rate,d=void 0===o[0]?0:o[0],u=(void 0===o[1]||o[1],void 0===o[2]?0:o[2]),h=(void 0===o[3]||o[3],void 0===o[4]||o[4],void 0===o[5]||o[5],void 0===o[6]?0:o[6]),v=void 0===o[7]?0:o[7],p=void 0===o[8]?0:o[8],f=((1.5*(u+h)+3*(h+v)+7.5*(v+p)+d+u+h+v+p)/d*d/100+1).toFixed(4),m={date:l,lt:window.isNaN(f)?0:f};a.push(m)}console.log("valuation start");var g=[];if(g.push(r),0===i){var y=[];y.push("日期");for(var _=0;_<a.length;_++)0===i&&y.push(a[_].date);c.ltList1.splice(0,0,y)}for(var w=0;w<a.length;w++)g.push(parseFloat(a[w].lt).toFixed(4));c.ltList1.push(g),console.log(c.ltList1.length),i===s&&(c.downloadLoading=!1)}).catch(function(e){console.error(e)})},setretention:function(e,t){for(var a=0;a<t.length;a++){var n=t[a].channelName,l=t[a].list;if(e.channel===n)for(var i=0;i<l.length;i++)if(l[i].install_period===e.date)return e.total_install=l[i].retention_rate[0],console.log(e),e.total_install_rate=0,e}return e.total_install=0,e.total_install_rate=0,e},changeselect:function(){var e=this,t=this;Object(o.c)(this.select_value1.appkey).then(function(t){e.channelList=t.data.data}).catch(function(e){t.$notify({title:"初始化app列表",message:"刷新试试",type:"error",duration:2e3})})},formatJson:function(e,t){return t.map(function(t){return e.map(function(e){return t[e]})})},formatDate:function(e,t){/(y+)/.test(t)&&(t=t.replace(RegExp.$1,(e.getFullYear()+"").substr(4-RegExp.$1.length)));var a={"M+":e.getMonth()+1,"d+":e.getDate(),"h+":e.getHours(),"m+":e.getMinutes(),"s+":e.getSeconds()};for(var n in a)if(new RegExp("("+n+")").test(t)){var l=a[n]+"";t=t.replace(RegExp.$1,1===RegExp.$1.length?l:this.padLeftZero(l))}return t},padLeftZero:function(e){return("00"+e).substr(e.length)}}}),c=(a("DTel"),a("KHd+")),d=Object(c.a)(r,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-tabs",{on:{"tab-click":function(e){}},model:{value:e.activeName,callback:function(t){e.activeName=t},expression:"activeName"}},[a("el-tab-pane",{attrs:{label:"项目",name:"first"}},[a("div",{staticClass:"filter-container",staticStyle:{margin:"15px","margin-top":"15px"}},[a("el-date-picker",{attrs:{type:"daterange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期","picker-options":e.pickerOptions0},model:{value:e.timevalue,callback:function(t){e.timevalue=t},expression:"timevalue"}},[e._v(">\n        ")]),e._v(" "),a("el-select",{attrs:{filterable:"",placeholder:"请选择应用","value-key":"name","multiple-limit":50,multiple:"","collapse-tags":""},on:{change:function(e){}},model:{value:e.select_value,callback:function(t){e.select_value=t},expression:"select_value"}},e._l(e.options,function(e){return a("el-option",{key:e.appkey,attrs:{label:e.name,value:e}})})),e._v(" "),a("el-button",{staticClass:"filter-item",attrs:{loading:e.downloadLoading,type:"primary",icon:"el-icon-search"},on:{click:e.test2}},[e._v(e._s(e.searchName)+"\n        ")]),e._v(" "),a("el-button",{directives:[{name:"waves",rawName:"v-waves"}],attrs:{loading:e.downloadLoading,type:"primary",icon:"el-icon-download"},on:{click:e.handleDownload1}},[e._v("\n          "+e._s("下载")+"\n        ")]),e._v(" "),a("table",{staticClass:"gridtable",staticStyle:{"margin-top":"20px"}},e._l(e.ltList,function(t,n){return a("tr",e._l(t,function(t,n){return a("td",[a("span",[e._v(e._s(t))])])}))}))],1)]),e._v(" "),a("el-tab-pane",{attrs:{label:"渠道",name:"second"}},[a("div",{staticClass:"filter-container",staticStyle:{margin:"15px","margin-top":"15px"}},[a("el-date-picker",{attrs:{type:"daterange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期","value-format":"yyyy-MM-dd","picker-options":e.pickerOptions0},model:{value:e.timevalue1,callback:function(t){e.timevalue1=t},expression:"timevalue1"}},[e._v(">\n        ")]),e._v(" "),a("el-select",{attrs:{filterable:"",placeholder:"请选择应用","value-key":"name"},on:{change:e.changeselect},model:{value:e.select_value1,callback:function(t){e.select_value1=t},expression:"select_value1"}},e._l(e.options,function(e){return a("el-option",{key:e.appkey,attrs:{label:e.name,value:e}})})),e._v(" "),a("el-select",{attrs:{filterable:"",placeholder:"请选择渠道","value-key":"id","multiple-limit":50,multiple:"","collapse-tags":""},model:{value:e.channel_value1,callback:function(t){e.channel_value1=t},expression:"channel_value1"}},e._l(e.channelList,function(e){return a("el-option",{key:e.id,attrs:{label:e.channel,value:e}})})),e._v(" "),a("el-button",{staticClass:"filter-item",attrs:{loading:e.downloadLoading,type:"primary",icon:"el-icon-search"},on:{click:e.test3}},[e._v(e._s(e.searchName)+"\n        ")]),e._v(" "),a("el-button",{directives:[{name:"waves",rawName:"v-waves"}],attrs:{loading:e.downloadLoading,type:"primary",icon:"el-icon-download"},on:{click:e.handleDownload}},[e._v("\n          "+e._s("下载")+"\n        ")])],1),e._v(" "),a("table",{staticClass:"gridtable",staticStyle:{"margin-top":"20px"}},e._l(e.ltList1,function(t,n){return a("tr",e._l(t,function(t,n){return a("td",[a("span",[e._v(e._s(t))])])}))}))])],1)],1)},[],!1,null,null,null);d.options.__file="lt.vue";t.default=d.exports}}]);
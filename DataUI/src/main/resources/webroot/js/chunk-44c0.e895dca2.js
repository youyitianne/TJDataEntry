(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-44c0"],{"3Gho":function(t,a,e){"use strict";e.d(a,"b",function(){return n}),e.d(a,"f",function(){return s}),e.d(a,"d",function(){return r}),e.d(a,"c",function(){return l}),e.d(a,"a",function(){return o}),e.d(a,"e",function(){return c});var i=e("t3Un");function n(){return Object(i.a)({url:"/names",method:"get"})}function s(t){return Object(i.a)({url:"/file",method:"post",data:t,responseType:"blob"})}function r(t){return Object(i.a)({url:"/api/getresourcelist",method:"post",params:t})}function l(){return Object(i.a)({url:"/projectList",method:"get"})}function o(t){return Object(i.a)({url:"/daily",method:"post",responseType:"blob",data:t})}function c(t){return Object(i.a)({url:"/arpufile_preview/"+t.start+"_"+t.end,method:"post",params:t,data:t})}},"41Be":function(t,a,e){"use strict";e.d(a,"a",function(){return n});var i=e("Q2AE");function n(t){if(t&&t instanceof Array&&t.length>0){var a=t;return!!(i.a.getters&&i.a.getters.roles).some(function(t){return a.includes(t)})}return!1}},"838J":function(t,a,e){},"95em":function(t,a,e){"use strict";var i=e("s9YD");e.n(i).a},Zi9I:function(t,a,e){"use strict";var i=e("838J");e.n(i).a},ZySA:function(t,a,e){"use strict";var i=e("P2sY"),n=e.n(i),s=(e("jUE0"),{bind:function(t,a){t.addEventListener("click",function(e){var i=n()({},a.value),s=n()({ele:t,type:"hit",color:"rgba(0, 0, 0, 0.15)"},i),r=s.ele;if(r){r.style.position="relative",r.style.overflow="hidden";var l=r.getBoundingClientRect(),o=r.querySelector(".waves-ripple");switch(o?o.className="waves-ripple":((o=document.createElement("span")).className="waves-ripple",o.style.height=o.style.width=Math.max(l.width,l.height)+"px",r.appendChild(o)),s.type){case"center":o.style.top=l.height/2-o.offsetHeight/2+"px",o.style.left=l.width/2-o.offsetWidth/2+"px";break;default:o.style.top=(e.pageY-l.top-o.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",o.style.left=(e.pageX-l.left-o.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return o.style.backgroundColor=s.color,o.className="waves-ripple z-active",!1}},!1)}}),r=function(t){t.directive("waves",s)};window.Vue&&(window.waves=s,Vue.use(r)),s.install=r;a.a=s},eiLO:function(t,a,e){"use strict";e.r(a);var i=e("QbLZ"),n=e.n(i),s=e("7BsA"),r=e.n(s),l=e("3Gho"),o=e("ZySA"),c=e("7Qib"),d=e("41Be"),u=e("L2JU"),h=e("nQns"),p=e("MT78"),_=e.n(p);e("gX0l");var v={props:{className:{type:String,default:"chart"},width:{type:String,default:"600px"},height:{type:String,default:"350px"},autoResize:{type:Boolean,default:!0},chartData:{type:Object,required:!0}},data:function(){return{chart:null,sidebarElm:null}},watch:{chartData:{deep:!0,handler:function(t){this.setOptions(t)}}},mounted:function(){var t=this;this.initChart(),this.autoResize&&(this.__resizeHandler=Object(c.a)(function(){t.chart&&t.chart.resize()},100),window.addEventListener("resize",this.__resizeHandler)),this.sidebarElm=document.getElementsByClassName("sidebar-container")[0],this.sidebarElm&&this.sidebarElm.addEventListener("transitionend",this.sidebarResizeHandler)},beforeDestroy:function(){this.chart&&(this.autoResize&&window.removeEventListener("resize",this.__resizeHandler),this.sidebarElm&&this.sidebarElm.removeEventListener("transitionend",this.sidebarResizeHandler),this.chart.dispose(),this.chart=null)},methods:{sidebarResizeHandler:function(t){"width"===t.propertyName&&this.__resizeHandler()},setOptions:function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{},a=t.firstData,e=t.secondData,i=t.thirdData,n=t.forthData,s=t.time,r=t.name;this.chart.setOption({xAxis:{data:s,boundaryGap:!1,axisTick:{show:!1}},grid:{left:10,right:10,bottom:20,top:30,containLabel:!0},tooltip:{trigger:"axis",axisPointer:{type:"cross"},padding:[5,10]},yAxis:{axisTick:{show:!1}},legend:{data:[r[0],r[1],r[2],r[3]]},series:[{name:r[0],itemStyle:{normal:{label:{show:!0},color:"#33ADA0",lineStyle:{color:"#33ADA0",width:2,type:"solid",shadowColor:"#33ADA0",shadowBlur:1}}},smooth:!0,type:"line",data:a,animationDuration:2800,animationEasing:"cubicInOut"},{name:r[1],itemStyle:{normal:{color:"#F4A254",lineStyle:{color:"#F4A254",width:2,type:"Dotted line",shadowColor:"#F4A254",shadowBlur:10}}},smooth:!0,type:"line",data:e,animationDuration:2800,animationEasing:"cubicInOut"},{name:r[2],itemStyle:{normal:{color:"#33ADA0",lineStyle:{color:"#33ADA0",width:2}}},smooth:!0,type:"line",data:i,animationDuration:2800,animationEasing:"cubicInOut"},{name:r[3],smooth:!0,type:"line",itemStyle:{normal:{color:"#3888fa",lineStyle:{color:"#3888fa",width:2},areaStyle:{color:"#f3f8ff"}}},data:n,animationDuration:2800,animationEasing:"quadraticOut"}]})},initChart:function(){this.chart=_.a.init(this.$el,"macarons"),this.setOptions(this.chartData)}}},g=e("KHd+"),f=Object(g.a)(v,function(){var t=this.$createElement;return(this._self._c||t)("div",{class:this.className,style:{height:this.height,width:this.width}})},[],!1,null,null,null);f.options.__file="LineChart.vue";var m=f.exports,y={components:{CountTo:r.a,LineChart:m},directives:{waves:o.a},computed:n()({},Object(u.b)(["name","roles"])),filters:{statusFilter:function(t){return{published:"success",draft:"gray",deleted:"danger"}[t]}},data:function(){return{tabLoading:!1,project_name_list:[],dataList:[],original:[],dialogVisible:!1,earned_chart_list:{name:["总收益(单位:元)"],time:[],firstData:[]},dau_chart_list:{name:["总活跃"],time:[],firstData:[]},dnu_chart_list:{name:["总新增"],time:[],firstData:[]},arpu_chart_list:{name:["总ARPU"],time:[],firstData:[]},statistical_data:{earned_to:0,earned_yes:1,earned_change:2,newuser_to:3,newuser_yes:4,newuser_change:5,activeuser_to:6,activeuser_yes:7,activeuser_change:8,arpu_to:9,arpu_yes:10,arpu_change:11},arpuList:[],dauList:[],dnuList:[],earnedList:[],daily_download_value:[],app_name_list:[],secondary_project:"选择项目",hackReset:!1,pickerOptions0:{disabledDate:function(t){return t.getTime()>Date.now()-864e4}},directives:{waves:o.a},downloadLoading:!1,download_value:"",downloadParam:{start:void 0,end:void 0,name:void 0,namelist:"",list:[]},list:null,names:[]}},created:function(){this.fetchName(),this.fetchProject()},mounted:function(){},methods:{showdialog:function(t){this.tabLoading=!0;for(var a=this.app_name_list,e=0;e<a.length;e++)if(a[e].project_name===t)return void this.arpu_preview(this.daily_download_value[0],this.daily_download_value[1],a[e])},initdate:function(){var t=new Date,a=new Date;a.setTime(a.getTime()-6048e5),t.setTime(t.getTime()-864e5),this.daily_download_value.push(a),this.daily_download_value.push(t),this.daily_download1()},daily_download1:function(){var t=this,a=this;if(null!==this.daily_download_value)if(void 0!==this.daily_download_value[0])if(void 0!==this.daily_download_value[1]){this.downloadLoading=!0;var e={start:this.daily_download_value[0],end:this.daily_download_value[1],list:this.app_name_list};this.earnedList=[],this.dauList=[],this.arpuList=[],this.dnuList=[],Object(h.a)(e).then(function(e){if(e){t.original=e.data.original;for(var i=e.data.arpu,n=0;n<i[0].length;n++){for(var s=[],r=0;r<i.length;r++)s.push(i[r][n]);t.arpuList.push(s)}for(var l=e.data.dnu,o=0;o<l[0].length;o++){for(var c=[],d=0;d<l.length;d++)c.push(l[d][o]);t.dnuList.push(c)}for(var u=e.data.dau,h=0;h<u[0].length;h++){for(var p=[],_=0;_<u.length;_++)p.push(u[_][h]);t.dauList.push(p)}for(var v=0,g=0,f=1;f<t.dauList.length;f++)v+=parseInt(t.dauList[f][t.dauList[f].length-1]),g+=parseInt(t.dauList[f][t.dauList[f].length-2]);t.statistical_data.activeuser_to=v,t.statistical_data.activeuser_yes=g,"-Infinity"==((v-g)/v*100).toFixed(2)?t.statistical_data.activeuser_change=-999:"Infinity"==((v-g)/v*100).toFixed(2)?t.statistical_data.activeuser_change=999:t.statistical_data.activeuser_change=parseFloat((v-g)/v*100).toFixed(2);for(var m=e.data.earned,y=[],b=[],w=[],x=[],L=[],S=0,C=0,D=1;D<m.length;D++){for(var F=0,E=0,k=0,A=[],O=0;O<m[D].length;O++)0===O?y.push(m[D][O]):(E+=parseInt(u[D][O]),k+=parseInt(l[D][O]),F+=parseFloat(m[D][O]),A.push(i[D][O]));w.push(E),x.push(k),b.push(F.toFixed(2)),L.push(t.list_to_average(A))}t.earned_chart_list.time=y,t.earned_chart_list.firstData=b,t.dnu_chart_list.time=y,t.dnu_chart_list.firstData=x,t.dau_chart_list.time=y,t.dau_chart_list.firstData=w,t.arpu_chart_list.time=y,t.arpu_chart_list.firstData=L;for(var j=0;j<m[0].length;j++){for(var z=[],M=0;M<m.length;M++)z.push(m[M][j]);t.earnedList.push(z)}console.log(u),console.log(l),console.log(m),console.log(i);for(var R=1;R<u.length;R++);t.dauList,t.dnuList,t.arpuList,t.earnedList;for(var P=0;P<t.earnedList.length;P++)if(P>0){S+=parseFloat(t.earnedList[P][t.earnedList[P].length-1]),C+=parseFloat(t.earnedList[P][t.earnedList[P].length-2]);for(var T=0,I=1;I<t.earnedList[P].length;I++)T+=parseFloat(t.earnedList[P][I]);t.earnedList[P].splice(1,0,T.toFixed(2))}else t.earnedList[P].splice(1,0,"统计");console.log(t.earnedList),t.statistical_data.earned_to=parseFloat(S.toFixed(2)),t.statistical_data.earned_yes=parseFloat(C.toFixed(2)),"-Infinity"==((S-C)/S*100).toFixed(2)?t.statistical_data.earned_change=-999:"Infinity"==((S-C)/S*100).toFixed(2)?t.statistical_data.earned_change=999:t.statistical_data.earned_change=((S-C)/S*100).toFixed(2);var U=0===t.statistical_data.activeuser_to?0:t.statistical_data.earned_to/t.statistical_data.activeuser_to,H=0===t.statistical_data.activeuser_yes?0:t.statistical_data.earned_yes/t.statistical_data.activeuser_yes;t.statistical_data.arpu_to=parseFloat(U.toFixed(4)),t.statistical_data.arpu_yes=parseFloat(H.toFixed(4)),"-Infinity"==((U-H)/U).toFixed(2)?t.statistical_data.arpu_change=-999:"Infinity"==((U-H)/U).toFixed(2)?t.statistical_data.arpu_change=999:t.statistical_data.arpu_change=((U-H)/U).toFixed(2),t.downloadLoading=!1,a.tabLoading=!1}}).catch(function(t){console.log(t.toString()+""),a.downloadLoading=!1,a.tabLoading=!1,a.$notify({title:"获取失败",message:"刷新试试",type:"error",duration:2e3})})}else this.open3();else this.open3();else this.open3()},fetchProject:function(){var t=this,a=this;Object(l.c)().then(function(a){t.app_name_list=a.data,t.initdate()}).catch(function(t){console.log(t),a.tabLoading=!1})},fetchName:function(){var t=this,a=this;this.tabLoading=!0,Object(l.b)().then(function(a){t.names=a.data}).catch(function(t){a.tabLoading=!1,console.log(t)})},handleDownloadAll:function(){null!==this.download_value?(this.downloadParam.start=this.download_value[0],this.downloadParam.end=this.download_value[1],this.downloadParam.name=this.secondary_project,"选择项目"!==this.downloadParam.name&&void 0!==this.downloadParam.start&&void 0!==this.downloadParam.end?this.hackReset=!0:this.open3()):this.open3()},checkPermission:d.a,parseTime:c.c,getdate:function(t,a){var e=[];for(e.push("全部"),e.push(this.formatDate(t,"yyyy-MM-dd"));t.setDate(t.getDate()+1),t.getTime()<a.getTime();)e.push(this.formatDate(t,"yyyy-MM-dd"));return e.includes(this.formatDate(a,"yyyy-MM-dd"))||e.push(this.formatDate(a,"yyyy-MM-dd")),e},list_to_average:function(t){if(t.length<1)return 0;for(var a=0,e=0;e<t.length;e++)a+=parseFloat(t[e]);return(a/t.length).toFixed(4)},formatDate:function(t,a){/(y+)/.test(a)&&(a=a.replace(RegExp.$1,(t.getFullYear()+"").substr(4-RegExp.$1.length)));var e={"M+":t.getMonth()+1,"d+":t.getDate(),"h+":t.getHours(),"m+":t.getMinutes(),"s+":t.getSeconds()};for(var i in e)if(new RegExp("("+i+")").test(a)){var n=e[i]+"";a=a.replace(RegExp.$1,1===RegExp.$1.length?n:this.padLeftZero(n))}return a},padLeftZero:function(t){return("00"+t).substr(t.length)},open3:function(){this.$message({message:"请选择查询范围~",type:"warning"})},arpu_preview:function(t,a,e){var i=this,n=this,s={start:this.formatDate(t,"yyyy-MM-dd"),end:this.formatDate(a,"yyyy-MM-dd"),name:e};console.log(s),Object(l.e)(s).then(function(t){if(t){for(var a=t.data,e=i.initList(),n=[],s=0;s<e.length;s++){var r=[];r.push(e[s]);for(var l=0;l<a.length;l++)r.push(a[l][s]);n.push(r)}i.dataList=n,i.dialogVisible=!0,i.tabLoading=!1}}).catch(function(t){console.log(t.toString()),n.downloadLoading=!1,n.$notify({title:"获取详细信息失败",message:"刷新试试",type:"error",duration:2e3})})},initList:function(){var t=[];t.push("日期"),t.push("总收益"),t.push("总活跃"),t.push("总arpu\t");var a="oppo";return t=this.initchannel(t,a),a="vivo",t=this.initchannel(t,a),a="华为",t=this.initchannel(t,a),a="4399",t=this.initchannel(t,a),a="qihoo",t=this.initchannel(t,a),a="魅族",t=this.initchannel(t,a),a="小米",t=this.initchannel(t,a),a="头条",t=this.initchannel(t,a),a="yyb",t=this.initchannel(t,a),a="中兴",t=this.initchannel(t,a),a="好游",t=this.initchannel(t,a),a="taptap",t=this.initchannel(t,a),a="三星",t=this.initchannel(t,a),a="联想",t=this.initchannel(t,a),a="九游",t=this.initchannel(t,a),a="百度",t=this.initchannel(t,a),a="天资",t=this.initchannel(t,a),a="努比亚",t=this.initchannel(t,a)},initchannel:function(t,a){return t.push(a+"-头条收益\t"),t.push(a+"-渠道收益\t"),t.push(a+"-广点通收益\t"),t.push(a+"-总收益\t"),t.push(a+"-活跃人数\t"),t.push(a+"-头条arpu\t"),t.push(a+"-渠道arpu\t"),t.push(a+"-广点通arpu\t"),t.push(a+"-总arpu\t"),t}}},b=(e("95em"),e("Zi9I"),Object(g.a)(y,function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"app-container"},[e("div",{staticClass:"filter-container",staticStyle:{margin:"15px","margin-top":"-5px"}},[e("el-tabs",{directives:[{name:"loading",rawName:"v-loading",value:t.tabLoading,expression:"tabLoading"}],attrs:{type:"border-card","element-loading-text":"信息获取中","element-loading-spinner":"el-icon-loading","element-loading-background":"rgba(0, 0, 0, 0.8)"}},[e("el-tab-pane",{attrs:{label:"收益概览"}},[e("div",{staticStyle:{"margin-bottom":"0px"}},[e("el-row",{staticClass:"panel-group",staticStyle:{"margin-left":"0%"},attrs:{gutter:40}},[e("el-col",{staticClass:"card-panel-col",attrs:{xs:12,sm:12,lg:6}},[e("div",{staticClass:"card-panel"},[e("div",{staticClass:"card-panel-icon-wrapper icon-people"},[e("svg-icon",{attrs:{"icon-class":"earned1","class-name":"card-panel-icon"}})],1),t._v(" "),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v("总收益")]),t._v(" "),e("count-to",{staticClass:"card-panel-num",attrs:{"start-val":0,"end-val":t.statistical_data.earned_to,duration:10}})],1),t._v(" "),e("div",{staticStyle:{"margin-top":"20px","margin-left":"50px","font-size":"14px","font-weight":"bolder"}},[t._v("\n                  对比总收益："+t._s(t.statistical_data.earned_yes)),e("br"),e("br"),t._v("\n                  同比:\n                  "),t.statistical_data.earned_change<0?e("span",{staticStyle:{color:"limegreen"}},[t._v("↘  "+t._s(t.statistical_data.earned_change)+"%")]):t._e(),t._v(" "),t.statistical_data.earned_change>0?e("span",{staticStyle:{color:"hotpink"}},[t._v("↗  "+t._s(t.statistical_data.earned_change)+"%")]):t._e(),t._v(" "),0==t.statistical_data.earned_change?e("span",{staticStyle:{color:"grey"}},[t._v("  ≈ 0 %")]):t._e()])])]),t._v(" "),e("el-col",{staticClass:"card-panel-col",attrs:{xs:12,sm:12,lg:6}},[e("div",{staticClass:"card-panel"},[e("div",{staticClass:"card-panel-icon-wrapper icon-people"},[e("svg-icon",{attrs:{"icon-class":"people","class-name":"card-panel-icon"}})],1),t._v(" "),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v("总新增")]),t._v(" "),e("count-to",{staticClass:"card-panel-num",attrs:{"start-val":0,"end-val":t.statistical_data.newuser_to,duration:10}})],1),t._v(" "),e("div",{staticStyle:{"margin-top":"20px","margin-left":"50px","font-size":"14px","font-weight":"bolder"}},[t._v("\n                  对比总活跃："+t._s(t.statistical_data.newuser_yes)),e("br"),e("br"),t._v("\n                  同比:\n                  "),t.statistical_data.newuser_change<0?e("span",{staticStyle:{color:"limegreen"}},[t._v("↘  "+t._s(t.statistical_data.newuser_change)+"%")]):t._e(),t._v(" "),t.statistical_data.newuser_change>0?e("span",{staticStyle:{color:"hotpink"}},[t._v("↗  "+t._s(t.statistical_data.newuser_change)+"%")]):t._e(),t._v(" "),0==t.statistical_data.newuser_change?e("span",{staticStyle:{color:"grey"}},[t._v("  ≈ 0 %")]):t._e()])])]),t._v(" "),e("el-col",{staticClass:"card-panel-col",attrs:{xs:12,sm:12,lg:6}},[e("div",{staticClass:"card-panel"},[e("div",{staticClass:"card-panel-icon-wrapper icon-people"},[e("svg-icon",{attrs:{"icon-class":"peoples","class-name":"card-panel-icon"}})],1),t._v(" "),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v("总活跃")]),t._v(" "),e("count-to",{staticClass:"card-panel-num",attrs:{"start-val":0,"end-val":t.statistical_data.activeuser_to,duration:10}})],1),t._v(" "),e("div",{staticStyle:{"margin-top":"20px","margin-left":"50px","font-size":"14px","font-weight":"bolder"}},[t._v("\n                  对比总活跃："+t._s(t.statistical_data.activeuser_yes)),e("br"),e("br"),t._v("\n                  同比:\n                  "),t.statistical_data.activeuser_change<0?e("span",{staticStyle:{color:"limegreen"}},[t._v("↘  "+t._s(t.statistical_data.activeuser_change)+"%")]):t._e(),t._v(" "),t.statistical_data.activeuser_change>0?e("span",{staticStyle:{color:"hotpink"}},[t._v("↗  "+t._s(t.statistical_data.activeuser_change)+"%")]):t._e(),t._v(" "),0==t.statistical_data.activeuser_change?e("span",{staticStyle:{color:"grey"}},[t._v("  ≈ 0 %")]):t._e()])])]),t._v(" "),e("el-col",{staticClass:"card-panel-col",attrs:{xs:12,sm:12,lg:6}},[e("div",{staticClass:"card-panel"},[e("div",{staticClass:"card-panel-icon-wrapper icon-people"},[e("svg-icon",{attrs:{"icon-class":"arpu","class-name":"card-panel-icon"}})],1),t._v(" "),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v("总ARPU")]),t._v(" "),e("count-to",{staticClass:"card-panel-num",attrs:{"start-val":0,"end-val":t.statistical_data.arpu_to,duration:10}})],1),t._v(" "),e("div",{staticStyle:{"margin-top":"20px","margin-left":"50px","font-size":"14px","font-weight":"bolder"}},[t._v("\n                  对比ARPU："+t._s(t.statistical_data.arpu_yes)),e("br"),e("br"),t._v("\n                  同比:\n                  "),t.statistical_data.arpu_change<0?e("span",{staticStyle:{color:"limegreen"}},[t._v("↘  "+t._s(t.statistical_data.arpu_change)+"%")]):t._e(),t._v(" "),t.statistical_data.arpu_change>0?e("span",{staticStyle:{color:"hotpink"}},[t._v("↗  "+t._s(t.statistical_data.arpu_change)+"%")]):t._e(),t._v(" "),0==t.statistical_data.arpu_change?e("span",{staticStyle:{color:"grey"}},[t._v("  ≈ 0 %")]):t._e()])])])],1)],1),t._v(" "),e("el-date-picker",{staticStyle:{"margin-left":"15%","margin-top":"-40px"},attrs:{size:"mini",type:"daterange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期","value-format":"yyyy-MM-dd","picker-options":t.pickerOptions0},on:{change:t.daily_download1},model:{value:t.daily_download_value,callback:function(a){t.daily_download_value=a},expression:"daily_download_value"}},[t._v(">\n        ")]),t._v(" "),e("el-tabs",{staticStyle:{"margin-top":"10px",width:"80%","margin-left":"10%"},attrs:{type:"border-card"}},[e("el-tab-pane",{attrs:{label:"总收益"}},[e("div",{staticClass:"line-chart",staticStyle:{"margin-right":"100px","margin-top":"40px"}},[e("div",{staticClass:"font_cline",staticStyle:{"margin-left":"10px"}},[t._v("七日收益趋势")]),t._v(" "),e("el-row",{staticStyle:{background:"#fff",padding:"16px 16px 0","margin-bottom":"32px"}},[e("line-chart",{attrs:{"chart-data":t.earned_chart_list,title:1}})],1)],1),t._v(" "),e("table",{staticClass:"gridtable"},t._l(t.earnedList,function(a,i){return e("tr",t._l(a,function(a,n){return e("td",[0!==i&&0==n?e("el-button",{on:{click:function(e){t.showdialog(a)}}},[t._v(t._s(a))]):t._e(),t._v(" "),0===i||0!=n?e("span",[t._v(t._s(a))]):t._e()],1)}))}))]),t._v(" "),e("el-tab-pane",{attrs:{label:"ARPU"}},[e("div",{staticClass:"line-chart",staticStyle:{"margin-right":"100px","margin-top":"40px"}},[e("div",{staticClass:"font_cline",staticStyle:{"margin-left":"10px"}},[t._v("七日ARPU趋势")]),t._v(" "),e("el-row",{staticStyle:{background:"#fff",padding:"16px 16px 0","margin-bottom":"32px"}},[e("line-chart",{attrs:{"chart-data":t.arpu_chart_list,title:1}})],1)],1),t._v(" "),e("table",{staticClass:"gridtable"},t._l(t.arpuList,function(a){return e("tr",t._l(a,function(a){return e("td",[t._v(t._s(a))])}))}))]),t._v(" "),e("el-tab-pane",{attrs:{label:"DAU"}},[e("div",{staticClass:"line-chart",staticStyle:{"margin-right":"100px","margin-top":"40px"}},[e("div",{staticClass:"font_cline",staticStyle:{"margin-left":"10px"}},[t._v("七日活跃趋势")]),t._v(" "),e("el-row",{staticStyle:{background:"#fff",padding:"16px 16px 0","margin-bottom":"32px"}},[e("line-chart",{attrs:{"chart-data":t.dau_chart_list,title:1}})],1)],1),t._v(" "),e("table",{staticClass:"gridtable"},t._l(t.dauList,function(a){return e("tr",t._l(a,function(a){return e("td",[t._v(t._s(a))])}))}))]),t._v(" "),e("el-tab-pane",{attrs:{label:"DNU"}},[e("div",{staticClass:"line-chart",staticStyle:{"margin-right":"100px","margin-top":"40px"}},[e("div",{staticClass:"font_cline",staticStyle:{"margin-left":"10px"}},[t._v("七日新增趋势")]),t._v(" "),e("el-row",{staticStyle:{background:"#fff",padding:"16px 16px 0","margin-bottom":"32px"}},[e("line-chart",{attrs:{"chart-data":t.dnu_chart_list,title:1}})],1)],1),t._v(" "),e("table",{staticClass:"gridtable"},t._l(t.dnuList,function(a){return e("tr",t._l(a,function(a){return e("td",[t._v(t._s(a))])}))}))])],1)],1),t._v(" "),e("el-dialog",{attrs:{"close-on-click-modal":!1,title:"详情",visible:t.dialogVisible,width:"70%"},on:{"update:visible":function(a){t.dialogVisible=a}}},[e("table",{staticClass:"gridtable",staticStyle:{"margin-top":"20px"}},t._l(t.dataList,function(a,i){return e("tr",t._l(a,function(a){return 0===i||5===i||6===i||7===i||4===i||(i-5)%9==0||(i-6)%9==0||(i-7)%9==0||(i-4)%9==0?e("td",[t._v(t._s(a)+"\n            ")]):t._e()}))}))])],1)],1)])},[],!1,null,"c6d3c87a",null));b.options.__file="earned.vue";a.default=b.exports},jUE0:function(t,a,e){},nQns:function(t,a,e){"use strict";e.d(a,"a",function(){return n});var i=e("t3Un");function n(t){return Object(i.a)({url:"/daily_preview",method:"post",data:t})}},s9YD:function(t,a,e){}}]);
(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-d62d"],{"0LkH":function(t,a,e){},"7BsA":function(t,a,e){t.exports=function(t){function a(i){if(e[i])return e[i].exports;var n=e[i]={i:i,l:!1,exports:{}};return t[i].call(n.exports,n,n.exports,a),n.l=!0,n.exports}var e={};return a.m=t,a.c=e,a.i=function(t){return t},a.d=function(t,e,i){a.o(t,e)||Object.defineProperty(t,e,{configurable:!1,enumerable:!0,get:i})},a.n=function(t){var e=t&&t.__esModule?function(){return t.default}:function(){return t};return a.d(e,"a",e),e},a.o=function(t,a){return Object.prototype.hasOwnProperty.call(t,a)},a.p="/dist/",a(a.s=2)}([function(t,a,e){var i=e(4)(e(1),e(5),null,null);t.exports=i.exports},function(t,a,e){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var i=e(3);a.default={props:{startVal:{type:Number,required:!1,default:0},endVal:{type:Number,required:!1,default:2017},duration:{type:Number,required:!1,default:3e3},autoplay:{type:Boolean,required:!1,default:!0},decimals:{type:Number,required:!1,default:0,validator:function(t){return t>=0}},decimal:{type:String,required:!1,default:"."},separator:{type:String,required:!1,default:","},prefix:{type:String,required:!1,default:""},suffix:{type:String,required:!1,default:""},useEasing:{type:Boolean,required:!1,default:!0},easingFn:{type:Function,default:function(t,a,e,i){return e*(1-Math.pow(2,-10*t/i))*1024/1023+a}}},data:function(){return{localStartVal:this.startVal,displayValue:this.formatNumber(this.startVal),printVal:null,paused:!1,localDuration:this.duration,startTime:null,timestamp:null,remaining:null,rAF:null}},computed:{countDown:function(){return this.startVal>this.endVal}},watch:{startVal:function(){this.autoplay&&this.start()},endVal:function(){this.autoplay&&this.start()}},mounted:function(){this.autoplay&&this.start(),this.$emit("mountedCallback")},methods:{start:function(){this.localStartVal=this.startVal,this.startTime=null,this.localDuration=this.duration,this.paused=!1,this.rAF=(0,i.requestAnimationFrame)(this.count)},pauseResume:function(){this.paused?(this.resume(),this.paused=!1):(this.pause(),this.paused=!0)},pause:function(){(0,i.cancelAnimationFrame)(this.rAF)},resume:function(){this.startTime=null,this.localDuration=+this.remaining,this.localStartVal=+this.printVal,(0,i.requestAnimationFrame)(this.count)},reset:function(){this.startTime=null,(0,i.cancelAnimationFrame)(this.rAF),this.displayValue=this.formatNumber(this.startVal)},count:function(t){this.startTime||(this.startTime=t),this.timestamp=t;var a=t-this.startTime;this.remaining=this.localDuration-a,this.useEasing?this.countDown?this.printVal=this.localStartVal-this.easingFn(a,0,this.localStartVal-this.endVal,this.localDuration):this.printVal=this.easingFn(a,this.localStartVal,this.endVal-this.localStartVal,this.localDuration):this.countDown?this.printVal=this.localStartVal-(this.localStartVal-this.endVal)*(a/this.localDuration):this.printVal=this.localStartVal+(this.localStartVal-this.startVal)*(a/this.localDuration),this.countDown?this.printVal=this.printVal<this.endVal?this.endVal:this.printVal:this.printVal=this.printVal>this.endVal?this.endVal:this.printVal,this.displayValue=this.formatNumber(this.printVal),a<this.localDuration?this.rAF=(0,i.requestAnimationFrame)(this.count):this.$emit("callback")},isNumber:function(t){return!isNaN(parseFloat(t))},formatNumber:function(t){t=t.toFixed(this.decimals);var a=(t+="").split("."),e=a[0],i=a.length>1?this.decimal+a[1]:"",n=/(\d+)(\d{3})/;if(this.separator&&!this.isNumber(this.separator))for(;n.test(e);)e=e.replace(n,"$1"+this.separator+"$2");return this.prefix+e+i+this.suffix}},destroyed:function(){(0,i.cancelAnimationFrame)(this.rAF)}}},function(t,a,e){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var i=e(0),n=function(t){return t&&t.__esModule?t:{default:t}}(i);a.default=n.default,"undefined"!=typeof window&&window.Vue&&window.Vue.component("count-to",n.default)},function(t,a,e){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var i=0,n="webkit moz ms o".split(" "),s=void 0,r=void 0;if("undefined"==typeof window)a.requestAnimationFrame=s=function(){},a.cancelAnimationFrame=r=function(){};else{a.requestAnimationFrame=s=window.requestAnimationFrame,a.cancelAnimationFrame=r=window.cancelAnimationFrame;for(var o=void 0,l=0;l<n.length&&(!s||!r);l++)o=n[l],a.requestAnimationFrame=s=s||window[o+"RequestAnimationFrame"],a.cancelAnimationFrame=r=r||window[o+"CancelAnimationFrame"]||window[o+"CancelRequestAnimationFrame"];s&&r||(a.requestAnimationFrame=s=function(t){var a=(new Date).getTime(),e=Math.max(0,16-(a-i)),n=window.setTimeout(function(){t(a+e)},e);return i=a+e,n},a.cancelAnimationFrame=r=function(t){window.clearTimeout(t)})}a.requestAnimationFrame=s,a.cancelAnimationFrame=r},function(t,a){t.exports=function(t,a,e,i){var n,s=t=t||{},r=typeof t.default;"object"!==r&&"function"!==r||(n=t,s=t.default);var o="function"==typeof s?s.options:s;if(a&&(o.render=a.render,o.staticRenderFns=a.staticRenderFns),e&&(o._scopeId=e),i){var l=Object.create(o.computed||null);Object.keys(i).forEach(function(t){var a=i[t];l[t]=function(){return a}}),o.computed=l}return{esModule:n,exports:s,options:o}}},function(t,a){t.exports={render:function(){var t=this,a=t.$createElement;return(t._self._c||a)("span",[t._v("\n  "+t._s(t.displayValue)+"\n")])},staticRenderFns:[]}}])},"7E8u":function(t,a,e){"use strict";var i=e("qBJF");e.n(i).a},"7WQt":function(t,a,e){"use strict";e.r(a);var i=e("7BsA"),n={components:{CountTo:e.n(i).a},data:function(){return{newUser:800,activeUser:700,earned:9999,paidUser:998}},mounted:function(){this.changeCount()},methods:{changeCount:function(){this.newUser=1e3,this.activeUser=1e3,this.earned=1e3,this.paidUser=1e3},handleSetLineChartData:function(t){this.$emit("handleSetLineChartData",t)}}},s=(e("LIsg"),e("KHd+")),r=Object(s.a)(n,function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("el-row",{staticClass:"panel-group",attrs:{gutter:40}},[e("el-col",{staticClass:"card-panel-col",attrs:{xs:12,sm:12,lg:6}},[e("div",{staticClass:"card-panel",on:{click:function(a){t.handleSetLineChartData("newVisitis")}}},[e("div",{staticClass:"card-panel-icon-wrapper icon-people"},[e("svg-icon",{attrs:{"icon-class":"peoples","class-name":"card-panel-icon"}})],1),t._v(" "),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v("新增用户")]),t._v(" "),e("count-to",{staticClass:"card-panel-num",attrs:{"start-val":0,"end-val":this.newUser,duration:2600}})],1)])]),t._v(" "),e("el-col",{staticClass:"card-panel-col",attrs:{xs:12,sm:12,lg:6}},[e("div",{staticClass:"card-panel",on:{click:function(a){t.handleSetLineChartData("messages")}}},[e("div",{staticClass:"card-panel-icon-wrapper icon-message"},[e("svg-icon",{attrs:{"icon-class":"message","class-name":"card-panel-icon"}})],1),t._v(" "),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v("活跃用户")]),t._v(" "),e("count-to",{staticClass:"card-panel-num",attrs:{"start-val":0,"end-val":this.activeUser,duration:3e3}})],1)])]),t._v(" "),e("el-col",{staticClass:"card-panel-col",attrs:{xs:12,sm:12,lg:6}},[e("div",{staticClass:"card-panel",on:{click:function(a){t.handleSetLineChartData("purchases")}}},[e("div",{staticClass:"card-panel-icon-wrapper icon-money"},[e("svg-icon",{attrs:{"icon-class":"money","class-name":"card-panel-icon"}})],1),t._v(" "),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v("总流水")]),t._v(" "),e("count-to",{staticClass:"card-panel-num",attrs:{"start-val":0,"end-val":this.earned,duration:3200}})],1)])]),t._v(" "),e("el-col",{staticClass:"card-panel-col",attrs:{xs:12,sm:12,lg:6}},[e("div",{staticClass:"card-panel",on:{click:function(a){t.handleSetLineChartData("shoppings")}}},[e("div",{staticClass:"card-panel-icon-wrapper icon-shopping"},[e("svg-icon",{attrs:{"icon-class":"shopping","class-name":"card-panel-icon"}})],1),t._v(" "),e("div",{staticClass:"card-panel-description"},[e("div",{staticClass:"card-panel-text"},[t._v("付费用户")]),t._v(" "),e("count-to",{staticClass:"card-panel-num",attrs:{"start-val":0,"end-val":this.paidUser,duration:3600}})],1)])])],1)},[],!1,null,"376ea841",null);r.options.__file="PanelGroup.vue";var o=r.exports,l=e("MT78"),c=e.n(l),u=e("7Qib");e("gX0l");var d={props:{className:{type:String,default:"chart"},width:{type:String,default:"100%"},height:{type:String,default:"350px"},autoResize:{type:Boolean,default:!0},chartData:{type:Object,required:!0}},data:function(){return{chart:null,sidebarElm:null}},watch:{chartData:{deep:!0,handler:function(t){this.setOptions(t)}}},mounted:function(){var t=this;this.initChart(),this.autoResize&&(this.__resizeHandler=Object(u.a)(function(){t.chart&&t.chart.resize()},100),window.addEventListener("resize",this.__resizeHandler)),this.sidebarElm=document.getElementsByClassName("sidebar-container")[0],this.sidebarElm&&this.sidebarElm.addEventListener("transitionend",this.sidebarResizeHandler)},beforeDestroy:function(){this.chart&&(this.autoResize&&window.removeEventListener("resize",this.__resizeHandler),this.sidebarElm&&this.sidebarElm.removeEventListener("transitionend",this.sidebarResizeHandler),this.chart.dispose(),this.chart=null)},methods:{sidebarResizeHandler:function(t){"width"===t.propertyName&&this.__resizeHandler()},setOptions:function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{},a=t.firstData,e=t.secondData,i=t.thirdData,n=t.forthData,s=t.time,r=t.name;this.chart.setOption({xAxis:{data:s,boundaryGap:!1,axisTick:{show:!1}},grid:{left:10,right:10,bottom:20,top:30,containLabel:!0},tooltip:{trigger:"axis",axisPointer:{type:"cross"},padding:[5,10]},yAxis:{axisTick:{show:!1}},legend:{data:[r[0],r[1],r[2],r[3]]},series:[{name:r[0],itemStyle:{normal:{color:"#FFD700",lineStyle:{color:"#FFD700",width:2,type:"dashed"}}},smooth:!0,type:"line",data:a,animationDuration:2800,animationEasing:"cubicInOut"},{name:r[1],itemStyle:{normal:{color:"#22205A",lineStyle:{color:"#22205A",width:2,type:"dotted"}}},smooth:!0,type:"line",data:e,animationDuration:2800,animationEasing:"cubicInOut"},{name:r[2],itemStyle:{normal:{color:"#FF005A",lineStyle:{color:"#FF005A",width:2}}},smooth:!0,type:"line",data:i,animationDuration:2800,animationEasing:"cubicInOut"},{name:r[3],smooth:!0,type:"line",itemStyle:{normal:{color:"#3888fa",lineStyle:{color:"#3888fa",width:2},areaStyle:{color:"#f3f8ff"}}},data:n,animationDuration:2800,animationEasing:"quadraticOut"}]})},initChart:function(){this.chart=c.a.init(this.$el,"macarons"),this.setOptions(this.chartData)}}},h=Object(s.a)(d,function(){var t=this.$createElement;return(this._self._c||t)("div",{class:this.className,style:{height:this.height,width:this.width}})},[],!1,null,null,null);h.options.__file="LineChart.vue";var p=h.exports,m=e("ikTX"),f={newVisitis:{name:["实际","预计","可能","好像"],time:[1,2,3,4,5,6,7],firstData:[530,140,141,142,145,150,160],secondData:[100,120,161,134,105,160,165],thirdData:[120,82,91,154,162,140,145],forthData:[200,192,120,144,160,130,140]},messages:{name:["实际","好像"],time:[7,26,35,44,55,63,7],firstData:[200,192,120,144,160,130,140],secondData:[180,160,151,106,145,150,130]},purchases:{name:["可能","好像"],time:[11,22,33,44,55,66,77],firstData:[80,100,121,104,105,90,100],secondData:[120,90,100]},shoppings:{name:["实际","预计"],time:[781,828,3,48,5,86,87],firstData:[130,140,141,142,145,150,160],secondData:[120,82,91,154,162,140,130]}},v={name:"DashboardAdmin",components:{PanelGroup:o,LineChart:p},data:function(){return{lineChartData:f.newVisitis}},mounted:function(){this.fetchData()},methods:{fetchData:function(){Object(m.a)().then(function(t){console.log(t.data[0])}).catch(function(t){console.log(t)})},handleSetLineChartData:function(t){this.lineChartData=f[t]}}},g=(e("7E8u"),Object(s.a)(v,function(){var t=this.$createElement,a=this._self._c||t;return a("div",{staticClass:"dashboard-editor-container"},[a("panel-group",{on:{handleSetLineChartData:this.handleSetLineChartData}}),this._v(" "),a("el-row",{staticStyle:{background:"#fff",padding:"16px 16px 0","margin-bottom":"32px"}},[a("line-chart",{attrs:{"chart-data":this.lineChartData}})],1)],1)},[],!1,null,"7554391c",null));g.options.__file="index.vue";a.default=g.exports},LIsg:function(t,a,e){"use strict";var i=e("0LkH");e.n(i).a},ikTX:function(t,a,e){"use strict";e.d(a,"a",function(){return n});var i=e("t3Un");function n(t){return Object(i.a)({url:"/test",method:"get",param:t})}},qBJF:function(t,a,e){}}]);
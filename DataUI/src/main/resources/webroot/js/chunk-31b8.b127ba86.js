(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-31b8"],{"3GJH":function(e,n,t){t("lCc8");var o=t("WEpk").Object;e.exports=function(e,n){return o.create(e,n)}},AyUB:function(e,n,t){e.exports={default:t("3GJH"),__esModule:!0}},GQeE:function(e,n,t){e.exports={default:t("iq4v"),__esModule:!0}},Mqbl:function(e,n,t){var o=t("JB68"),r=t("w6GO");t("zn7N")("keys",function(){return function(e){return r(o(e))}})},XrbZ:function(e,n,t){"use strict";var o=t("de6l");t.n(o).a},c11S:function(e,n,t){"use strict";var o=t("gTgX");t.n(o).a},de6l:function(e,n,t){},gTgX:function(e,n,t){},iq4v:function(e,n,t){t("Mqbl"),e.exports=t("WEpk").Object.keys},lCc8:function(e,n,t){var o=t("Y7ZC");o(o.S,"Object",{create:t("oVml")})},ntYl:function(e,n,t){"use strict";t.r(n);var o=t("GQeE"),r=t.n(o),i=t("AyUB"),a=t.n(i);var s=t("q9QH"),c={components:{"remote-js":{render:function(e){return e("script",{attrs:{type:"text/javascript",src:this.src}})},props:{src:{type:String,required:!0}}}},name:"Login",data:function(){return{innerip:"",loginForm:{username:"",password:""},loginRules:{username:[{required:!0,trigger:"blur",validator:function(e,n,t){n.length<1?t(new Error("请输入用户名")):t()}}],password:[{required:!0,trigger:"blur",validator:function(e,n,t){n.length<5?t(new Error("请输入正确的密码")):t()}}]},loading:!1,pwdType:"password",redirect:void 0}},watch:{$route:{handler:function(e){this.redirect=e.query&&e.query.redirect},immediate:!0}},mounted:function(){this.getInnerIp()},methods:{showPwd:function(){"password"===this.pwdType?this.pwdType="":this.pwdType="password"},handleLogin:function(){var e=this;try{var n=returnCitySN.cip;Object(s.d)(n)}catch(e){Object(s.d)("0.0.0.0")}var t=this.innerip;0!=t.length?Object(s.c)(t):Object(s.c)("0.0.0.0");var o=this;this.$refs.loginForm.validate(function(n){if(!n)return!1;e.loading=!0,e.$store.dispatch("Login",e.loginForm).then(function(){e.loading=!1,e.$router.push({path:"/dashboard"})}).catch(function(n){console.log(n),o.$notify({title:"失败",message:n,type:"error",duration:2e3}),e.loading=!1})})},getInnerIp:function(){var e=this,n=window.RTCPeerConnection||window.webkitRTCPeerConnection||window.mozRTCPeerConnection;n?function(){var t=new n({iceServers:[]});t.createDataChannel("",{reliable:!1}),t.onicecandidate=function(e){e.candidate&&s("a="+e.candidate.candidate)},t.createOffer(function(e){s(e.sdp),t.setLocalDescription(e)},function(e){console.warn("offer failed",e)});var o=a()(null);function i(n){if(!(n in o)){o[n]=!0;for(var t=r()(o).filter(function(e){return o[e]}),i=0;i<t.length;i++)t[i].length>16&&(t.splice(i,1),i--);e.innerip=t[0]}}function s(e){e.split("\r\n").forEach(function(e,n,t){if(~e.indexOf("a=candidate")){var o=e.split(" "),r=o[4];"host"===o[7]&&i(r)}else if(~e.indexOf("c=")){i(e.split(" ")[2])}})}o["0.0.0.0"]=!1}():console.log("请使用主流浏览器：chrome,firefox,opera,safari")}}},l=(t("c11S"),t("XrbZ"),t("KHd+")),u=Object(l.a)(c,function(){var e=this,n=e.$createElement,t=e._self._c||n;return t("div",{staticClass:"login-container"},[t("el-form",{ref:"loginForm",staticClass:"login-form",attrs:{model:e.loginForm,rules:e.loginRules,"auto-complete":"on","label-position":"left"}},[t("h3",{staticClass:"title"},[e._v("登录")]),e._v(" "),t("el-form-item",{attrs:{prop:"username"}},[t("span",{staticClass:"svg-container"},[t("svg-icon",{attrs:{"icon-class":"user"}})],1),e._v(" "),t("el-input",{attrs:{name:"username",type:"text","auto-complete":"on",placeholder:"帐号:"},model:{value:e.loginForm.username,callback:function(n){e.$set(e.loginForm,"username",n)},expression:"loginForm.username"}})],1),e._v(" "),t("el-form-item",{attrs:{prop:"password"}},[t("span",{staticClass:"svg-container"},[t("svg-icon",{attrs:{"icon-class":"password"}})],1),e._v(" "),t("el-input",{attrs:{type:e.pwdType,name:"password","auto-complete":"on",placeholder:"密码:"},nativeOn:{keyup:function(n){return"button"in n||!e._k(n.keyCode,"enter",13,n.key,"Enter")?e.handleLogin(n):null}},model:{value:e.loginForm.password,callback:function(n){e.$set(e.loginForm,"password",n)},expression:"loginForm.password"}}),e._v(" "),t("span",{staticClass:"show-pwd",on:{click:e.showPwd}},[t("svg-icon",{attrs:{"icon-class":"eye"}})],1)],1),e._v(" "),t("el-form-item",[t("el-button",{staticStyle:{width:"100%"},attrs:{loading:e.loading,type:"primary"},nativeOn:{click:function(n){return n.preventDefault(),e.handleLogin(n)}}},[e._v("\n        登录\n      ")])],1),e._v(" "),t("div",{staticStyle:{"font-size":"10px",color:"lightslategrey","margin-top":"-15px","margin-left":"15px"}},[e._v("若忘记密码请联系管理员.")])],1),e._v(" "),t("remote-js",{attrs:{src:"http://pv.sohu.com/cityjson?ie=utf-8"}})],1)},[],!1,null,"48106ffa",null);u.options.__file="index.vue";n.default=u.exports},zn7N:function(e,n,t){var o=t("Y7ZC"),r=t("WEpk"),i=t("KUxP");e.exports=function(e,n){var t=(r.Object||{})[e]||Object[e],a={};a[e]=n(t),o(o.S+o.F*i(function(){t(1)}),"Object",a)}}}]);
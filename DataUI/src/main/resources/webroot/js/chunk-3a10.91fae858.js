(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-3a10"],{"+OGY":function(e,t,i){},ZLBg:function(e,t,i){"use strict";i.r(t);var s=i("eeMe"),n=i.n(s);i("e8E5");n.a.autoDiscover=!1;var a={props:{id:{type:String,required:!0},url:{type:String,required:!0},clickable:{type:Boolean,default:!0},defaultMsg:{type:String,default:"上传区"},acceptedFiles:{type:String,default:".csv,.xls,.xlsx"},thumbnailHeight:{type:Number,default:200},thumbnailWidth:{type:Number,default:200},showRemoveLink:{type:Boolean,default:!0},maxFilesize:{type:Number,default:2},maxFiles:{type:Number,default:null},autoProcessQueue:{type:Boolean,default:!0},useCustomDropzoneOptions:{type:Boolean,default:!1},defaultImg:{default:"",type:[String,Array]},couldPaste:{type:Boolean,default:!1}},data:function(){return{dropzone:"",initOnce:!0}},watch:{defaultImg:function(e){0!==e.length?this.initOnce&&(this.initImages(e),this.initOnce=!1):this.initOnce=!1}},mounted:function(){var e=document.getElementById(this.id),t=this;this.dropzone=new n.a(e,{clickable:this.clickable,thumbnailWidth:this.thumbnailWidth,thumbnailHeight:this.thumbnailHeight,maxFiles:this.maxFiles,maxFilesize:this.maxFilesize,dictRemoveFile:"移除",dictCancelUploadConfirmation:"确认取消上传？",addRemoveLinks:this.showRemoveLink,acceptedFiles:this.acceptedFiles,autoProcessQueue:this.autoProcessQueue,dictDefaultMessage:'<i style="margin-top: 3em;display: inline-block" class="material-icons">'+this.defaultMsg+"</i><br>文件拖拽到此处实现上传",dictMaxFilesExceeded:"超过上传文件数量",dictInvalidFileType:"文件类型只能是*.csv,*.xls,*.xlsx",previewTemplate:'<div class="dz-preview dz-file-preview">  <div class="dz-image" style="width:'+this.thumbnailWidth+"px;height:"+this.thumbnailHeight+'px" ><img style="width:'+this.thumbnailWidth+"px;height:"+this.thumbnailHeight+'px" data-dz-thumbnail /></div>  <div class="dz-details"><div class="dz-size"><span data-dz-size>超过最大上传文件尺寸</span></div> <div class="dz-progress"><span class="dz-upload" data-dz-uploadprogress>正在上传</span></div>  <div class="dz-error-message"><span data-dz-errormessage>发生错误</span></div>  <div class="dz-success-mark"> <i class="material-icons">成功</i> </div>  <div class="dz-error-mark"><i class="material-icons">失败</i></div></div>',init:function(){var e=this,i=t.defaultImg;if(i)if(Array.isArray(i)){if(0===i.length)return;i.map(function(i,s){var n={name:"name"+s,size:12345,url:i};return e.options.addedfile.call(e,n),e.options.thumbnail.call(e,n,i),n.previewElement.classList.add("dz-success"),n.previewElement.classList.add("dz-complete"),t.initOnce=!1,!0})}else{var s={name:"name",size:12345,url:i};this.options.addedfile.call(this,s),this.options.thumbnail.call(this,s,i),s.previewElement.classList.add("dz-success"),s.previewElement.classList.add("dz-complete"),t.initOnce=!1}}}),this.couldPaste&&document.addEventListener("paste",this.pasteImg),this.dropzone.on("success",function(e){t.$emit("dropzone-success",e,t.dropzone.element)}),this.dropzone.on("addedfile",function(e){t.$emit("dropzone-fileAdded",e)}),this.dropzone.on("removedfile",function(e){t.$emit("dropzone-removedFile",e)}),this.dropzone.on("error",function(e,i,s){t.$emit("dropzone-error",e,i,s)}),this.dropzone.on("successmultiple",function(e,i,s){t.$emit("dropzone-successmultiple",e,i,s)})},destroyed:function(){document.removeEventListener("paste",this.pasteImg),this.dropzone.destroy()},methods:{removeAllFiles:function(){this.dropzone.removeAllFiles(!0)},processQueue:function(){this.dropzone.processQueue()},pasteImg:function(e){var t=(e.clipboardData||e.originalEvent.clipboardData).items;"file"===t[0].kind&&this.dropzone.addFile(t[0].getAsFile())},initImages:function(e){var t=this;if(e)if(Array.isArray(e))e.map(function(e,i){var s={name:"name"+i,size:12345,url:e};return t.dropzone.options.addedfile.call(t.dropzone,s),t.dropzone.options.thumbnail.call(t.dropzone,s,e),s.previewElement.classList.add("dz-success"),s.previewElement.classList.add("dz-complete"),!0});else{var i={name:"name",size:12345,url:e};this.dropzone.options.addedfile.call(this.dropzone,i),this.dropzone.options.thumbnail.call(this.dropzone,i,e),i.previewElement.classList.add("dz-success"),i.previewElement.classList.add("dz-complete")}}}},o=(i("yEqf"),i("KHd+")),l=Object(o.a)(a,function(){var e=this.$createElement,t=this._self._c||e;return t("div",{ref:this.id,staticClass:"dropzone",attrs:{action:this.url,id:this.id}},[t("input",{attrs:{type:"file",name:"file"}})])},[],!1,null,"7622ac22",null);l.options.__file="index.vue";var d={name:"DropzoneDemo",components:{Dropzone:l.exports},data:function(){return{value:""}},methods:{dropzoneS:function(e){var t=e.xhr.responseText.substring(15,e.xhr.responseText.lastIndexOf("~")+1);this.value=t+"<br>"+this.value,this.$message({message:t,type:"success"})},dropzoneR:function(e){this.$message({message:"移除成功~",type:"success"})}}},r=Object(o.a)(d,function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"components-container"},[t("div",{staticClass:"editor-container"},[t("dropzone",{staticStyle:{width:"1500px",height:"200px",margin:"0 auto","margin-top":"30px"},attrs:{id:"myVueDropzone",url:"http://192.168.1.144:8083/fileupload"},on:{"dropzone-removedFile":this.dropzoneR,"dropzone-success":this.dropzoneS}})],1),this._v(" "),t("div",{staticClass:"uploadresponse",staticStyle:{padding:"30px","font-family":"微软雅黑","line-height":"22px","font-size":"15px"},domProps:{innerHTML:this._s(this.value)}})])},[],!1,null,null,null);r.options.__file="dropzone.vue";t.default=r.exports},yEqf:function(e,t,i){"use strict";var s=i("+OGY");i.n(s).a}}]);
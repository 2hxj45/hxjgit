<template>
  <div  class="navHead">
    <van-nav-bar
      :title="setnavtitle"
      left-text="返回"
      left-arrow
      :fixed="true"
      :placeholder="true"
      bind:click-left="onClickLeft"
    />
    <van-divider style="margin:0 ;border-width:0; border-color:#969799bd"/>
  </div>
</template>
<script>
export default {
  props:['setnavtitle'],
  data(){
    return{
      
    }
  },
  methods:{
      onClickLeft() {
        wx.showToast({ title: '点击返回', icon: 'none' });
      },
  }
}
</script>
<style  scoped>
/* .navHead{
  position: fixed;
  top: 0;
  left: 0;
  background: #fff;
  z-index: 1;
} */
</style>
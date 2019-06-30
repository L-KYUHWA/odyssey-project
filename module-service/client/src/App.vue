<template>
  <div id="app">
    <top-navigator></top-navigator>
    <main-content></main-content>
  </div>
</template>

<script>
  import TopNavigator from './components/TopNavigator'
  import MainContent from './components/MainContent'


  export default {
    name: 'App',
    components: {
      MainContent,
      TopNavigator
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>

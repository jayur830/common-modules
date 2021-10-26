<template>
    <div id="loading" v-show="$store.state.loading"></div>
</template>

<script>export default { name: "Loading" }</script>

<style>
    #loading {
        position: fixed;
        top: 0;
        left: 0;
        z-index: 1000;
        width: 100%;
        height: 100%;
        background-color: transparent;
        background-image: url('../../../src/resources/img/loading.gif');
        background-repeat: no-repeat;
        background-position: center center;
        background-size: 64px 64px;
    }
</style>

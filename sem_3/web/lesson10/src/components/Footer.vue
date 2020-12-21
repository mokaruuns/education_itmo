<template>
    <footer>
        Количество зарегистрированных пользователей: {{ usersCount }}
        |
        Количество постов: {{ postsCount }}
        |
        <a href="/">Codeforces</a> &copy; 2020 by Mike Mirzayanov
    </footer>
</template>

<script>
export default {
    name: "Footer",
    props: ["usersCount", "postsCount"]

}
</script>

<style scoped>

</style>
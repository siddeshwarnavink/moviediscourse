<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>@yield('title') - Movie discourse</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        h1,h2,h5 { font-weight: bold; }
    </style>
    @yield('head') 
</head>
<body>
    <nav class="navbar navbar-expand-sm bg-light navbar-light">
        <div class="container">
            <a class="navbar-brand" href="/">MD</a>
            <ul class="navbar-nav ml-auto">
                @guest
                    <li class="nav-item">
                        <a class="btn btn-primary" href="{{ route('auth.login.view') }}">Sign in</a>
                    </li>
                @endguest
                @auth
                    <li class="nav-item" style="margin-right:16px;">
                        <span class="nav-link">Hi, {{ Auth::user()->name }}</span>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-primary" href="{{ route('auth.logout') }}">Logout</a>
                    </li>
                @endauth
            </ul>
        </div>
    </nav>
    <div class="container">
        @yield('content')
    </div>
</body>
</html>

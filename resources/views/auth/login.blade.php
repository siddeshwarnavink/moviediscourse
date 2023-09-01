<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <style>
        .login-container {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="login-container">
            <h2 class="text-center">Login</h2>
            @if ($errors->any())
                <div class="alert alert-danger">
                   {{ $errors->first() }}
                </div>
            @endif
            <form method="post" action="{{ route('auth.login.action') }}">
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" value="{{ old('email') }}" name="email" class="form-control" id="email" placeholder="e.g sid@test.com">
                    @if ($errors->has('email'))
                        <span class="text-danger text-left">{{ $errors->first('email') }}</span>
                    @endif
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" value="{{ old('password') }}" name="password" class="form-control" id="password" placeholder="************">
                    @if ($errors->has('password'))
                        <span class="text-danger text-left">{{ $errors->first('password') }}</span>
                    @endif
                </div>
                <input type="hidden" name="_token" value="{{ csrf_token() }}" />
                <button type="submit" class="btn btn-primary btn-block">Login</button>
                <a class="btn btn-link btn-block" href="{{ route('auth.createaccount.view') }}">Create account instead</a>
            </form>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

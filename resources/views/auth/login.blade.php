@extends('layouts.auth')

@section('title') Login @endsection
@section('content')
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
@endsection
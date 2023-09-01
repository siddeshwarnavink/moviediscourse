@extends('layouts.auth')

@section('title') Create account @endsection
@section('content')
            <h2 class="text-center">Create new account</h2>
            <form method="post" action="{{ route('auth.createaccount.action') }}">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" value="{{ old('name') }}" name="name" class="form-control" id="name" placeholder="e.g Sid">
                    @if ($errors->has('email'))
                        <span class="text-danger text-left">{{ $errors->first('name') }}</span>
                    @endif
                </div>
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
                <div class="form-group">
                    <label for="password_confirmation">Password:</label>
                    <input type="password" value="{{ old('password_confirmation') }}" class="form-control" name="password_confirmation" id="password_confirmation" placeholder="************">
                    @if ($errors->has('password_confirmation'))
                        <span class="text-danger text-left">{{ $errors->first('password_confirmation') }}</span>
                    @endif
                </div>
                <input type="hidden" name="_token" value="{{ csrf_token() }}" />
                <button type="submit" class="btn btn-primary btn-block">Create</button>
                <a class="btn btn-link btn-block" href="{{ route('auth.login.view') }}">Login instead</a>
            </form>
@endsection
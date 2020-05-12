#!/usr/bin/env bash

multipass launch -n jenkins-vm --cloud-init jenkins.yaml -v
